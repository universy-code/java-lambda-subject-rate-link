package app.universy.course.rate.consumer;

import app.universy.course.rate.builders.InstitutionCourseRateBuilder;
import app.universy.course.rate.dao.institution.course.rate.InstitutionCourseRateDAO;
import app.universy.course.rate.model.institution.CareerKey;
import app.universy.course.rate.model.institution.InstitutionCourseRate;
import app.universy.course.rate.model.institution.tag.Tag;
import app.universy.course.rate.model.institution.tag.TagRate;
import app.universy.course.rate.model.student.StudentCareerKey;
import app.universy.course.rate.model.student.StudentCourseRate;
import app.universy.lambda.handlers.dynamo.consumers.ModifyConsumer;

import java.util.Map;
import java.util.stream.Collectors;

public class StudentCourseRateModifyConsumer extends ModifyConsumer<StudentCourseRate> {


    private final InstitutionCourseRateDAO instCourseRateDAO;

    public StudentCourseRateModifyConsumer(InstitutionCourseRateDAO instCourseRateDAO) {
        this.instCourseRateDAO = instCourseRateDAO;
    }

    @Override
    protected void modify(StudentCourseRate oldItem, StudentCourseRate newItem) {

        StudentCareerKey studentCareerKey = newItem.getStudentCareerKey();
        CareerKey careerKey = studentCareerKey.getCareerKey();

        String courseCode = newItem.getCourseCode();

        InstitutionCourseRate courseRate = instCourseRateDAO.fetch(courseCode)
                .orElse(new InstitutionCourseRateBuilder()
                        .withCareerKey(careerKey)
                        .withCourseCode(courseCode)
                        .build());

        updateOverallRate(courseRate, newItem, oldItem);
        updateDifficultyRate(courseRate, newItem, oldItem);
        updateWouldTakeAgain(courseRate, newItem, oldItem);
        updateTagsRate(courseRate, newItem, oldItem);

        instCourseRateDAO.save(courseRate);
    }

    private void updateOverallRate(InstitutionCourseRate courseRate, StudentCourseRate newItem, StudentCourseRate oldItem) {
        String newRate = Integer.toString(newItem.getOverall());
        String oldRate = Integer.toString(oldItem.getOverall());
        courseRate.getOverall().computeIfPresent(oldRate, (tag, rate) -> rate - 1);
        courseRate.getOverall().compute(newRate, (tag, rate) -> rate != null ? rate + 1 : 1);
    }


    private void updateDifficultyRate(InstitutionCourseRate courseRate, StudentCourseRate newItem, StudentCourseRate oldItem) {
        String newRate = Integer.toString(newItem.getDifficulty());
        String oldRate = Integer.toString(oldItem.getDifficulty());
        courseRate.getDifficulty().computeIfPresent(oldRate, (tag, rate) -> rate - 1);
        courseRate.getDifficulty().compute(newRate, (tag, rate) -> rate != null ? rate + 1 : 1);
    }

    private void updateWouldTakeAgain(InstitutionCourseRate instRate, StudentCourseRate newItem, StudentCourseRate oldItem) {
        if (newItem.wouldTakeAgain()) {
            instRate.incrementWouldTakeAgain();
        } else {
            instRate.incrementWouldNotTakeAgain();
        }

        if (oldItem.wouldTakeAgain()) {
            instRate.decrementWouldNotTakeAgain();
        } else {
            instRate.decrementWouldNotTakeAgain();
        }
    }

    private void updateTagsRate(InstitutionCourseRate courseRate, StudentCourseRate newItem, StudentCourseRate oldItem) {
        Map<Tag, Integer> institutionTags = courseRate.getTagsRate()
                .stream()
                .collect(Collectors.toMap(
                        TagRate::getTag,
                        TagRate::getRates
                ));

        newItem.getTags()
                .forEach((newTag) -> institutionTags
                        .compute(newTag, (tag, rate) -> rate != null ? rate + 1 : 1)
                );

        oldItem.getTags()
                .forEach((oldTag) -> institutionTags
                        .computeIfPresent(oldTag, (tag, rate) -> rate - 1)
                );

        courseRate.setTagsRate(institutionTags.entrySet()
                .stream()
                .map((e) -> new TagRate(e.getKey(), e.getValue()))
                .collect(Collectors.toList()));
    }


    @Override
    protected Class<StudentCourseRate> itemClass() {
        return StudentCourseRate.class;
    }
}
