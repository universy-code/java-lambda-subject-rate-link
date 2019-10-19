package app.universy.subject.rate.consumer;

import app.universy.lambda.handlers.dynamo.consumers.InsertConsumer;
import app.universy.subject.rate.dao.institution.subject.rate.InstitutionSubjectRateDAO;
import app.universy.subject.rate.model.institution.CareerKey;
import app.universy.subject.rate.model.institution.InstitutionSubjectRate;
import app.universy.subject.rate.model.student.StudentCareerKey;
import app.universy.subject.rate.model.student.StudentSubjectRate;

public class StudentSubjectRateInsertConsumer extends InsertConsumer<StudentSubjectRate> {

    private final InstitutionSubjectRateDAO instSubjectRateDAO;

    public StudentSubjectRateInsertConsumer(InstitutionSubjectRateDAO instSubjectRateDAO) {
        this.instSubjectRateDAO = instSubjectRateDAO;
    }

    @Override
    protected void insert(StudentSubjectRate newItem) {

        StudentCareerKey studentCareerKey = newItem.getStudentCareerKey();
        CareerKey careerKey = studentCareerKey.getCareerKey();

        String subjectCode = newItem.getSubjectCode();
        String rate = Integer.toString(newItem.getRate());

        InstitutionSubjectRate subjectRate = instSubjectRateDAO.fetchOrCreate(subjectCode, careerKey);

        subjectRate.getRates().compute(rate, (key, val) -> val != null ? val + 1 : 1);

        instSubjectRateDAO.save(subjectRate);
    }

    @Override
    protected Class<StudentSubjectRate> itemClass() {
        return StudentSubjectRate.class;
    }
}
