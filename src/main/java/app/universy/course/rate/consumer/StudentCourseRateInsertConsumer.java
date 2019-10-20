package app.universy.course.rate.consumer;

import app.universy.course.rate.builders.InstitutionCourseRateBuilder;
import app.universy.course.rate.dao.institution.course.rate.InstitutionCourseRateDAO;
import app.universy.course.rate.model.institution.CareerKey;
import app.universy.course.rate.model.institution.InstitutionCourseRate;
import app.universy.course.rate.model.institution.tag.Tag;
import app.universy.course.rate.model.institution.tag.TagRate;
import app.universy.course.rate.model.student.StudentCareerKey;
import app.universy.course.rate.model.student.StudentCourseRate;
import app.universy.lambda.handlers.dynamo.consumers.InsertConsumer;

import java.util.Map;
import java.util.stream.Collectors;

public class StudentCourseRateInsertConsumer extends InsertConsumer<StudentCourseRate> {

    private final InstitutionCourseRateDAO instCourseRateDAO;

    public StudentCourseRateInsertConsumer(InstitutionCourseRateDAO instCourseRateDAO) {
        this.instCourseRateDAO = instCourseRateDAO;
    }

    @Override
    protected void insert(StudentCourseRate newItem) {

        StudentCareerKey studentCareerKey = newItem.getStudentCareerKey();
        CareerKey careerKey = studentCareerKey.getCareerKey();

        String courseCode = newItem.getCourseCode();

        InstitutionCourseRate courseRate = instCourseRateDAO.fetch(courseCode)
                .orElse(new InstitutionCourseRateBuilder()
                        .withCareerKey(careerKey)
                        .withCourseCode(courseCode)
                        .build());

        updateOverallRate(courseRate, newItem);
        updateDifficultyRate(courseRate, newItem);
        updateWouldTakeAgain(courseRate, newItem);
        updateTagsRate(courseRate, newItem);

        instCourseRateDAO.save(courseRate);
    }

    private void updateOverallRate(InstitutionCourseRate courseRate, StudentCourseRate newItem) {
        String rate = Integer.toString(newItem.getOverall());
        courseRate.getOverall().compute(rate, (key, val) -> val != null ? val + 1 : 1);
    }


    private void updateDifficultyRate(InstitutionCourseRate courseRate, StudentCourseRate newItem) {
        String rate = Integer.toString(newItem.getDifficulty());
        courseRate.getDifficulty().compute(rate, (key, val) -> val != null ? val + 1 : 1);
    }

    private void updateWouldTakeAgain(InstitutionCourseRate instRate, StudentCourseRate studentRate) {
        if (studentRate.wouldTakeAgain()) {
            instRate.incrementWouldTakeAgain();
        } else {
            instRate.incrementWouldNotTakeAgain();
        }
    }

    private void updateTagsRate(InstitutionCourseRate courseRate, StudentCourseRate newItem) {
        System.out.println("Updating rate");
        Map<Tag, Integer> institutionTags = courseRate.getTagsRate()
                .stream()
                .collect(Collectors.toMap(
                        TagRate::getTag,
                        TagRate::getRates
                ));

        newItem.getTags()
                .forEach((tag) -> institutionTags
                        .compute(tag, (key, val) -> val != null ? val + 1 : 1)
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
