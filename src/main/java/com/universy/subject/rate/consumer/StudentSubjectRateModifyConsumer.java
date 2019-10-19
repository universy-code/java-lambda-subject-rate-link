package com.universy.subject.rate.consumer;

import app.universy.lambda.handlers.dynamo.consumers.ModifyConsumer;
import com.universy.subject.rate.dao.institution.subject.rate.InstitutionSubjectRateDAO;
import com.universy.subject.rate.model.institution.CareerKey;
import com.universy.subject.rate.model.institution.InstitutionSubjectRate;
import com.universy.subject.rate.model.student.StudentCareerKey;
import com.universy.subject.rate.model.student.StudentSubjectRate;

public class StudentSubjectRateModifyConsumer extends ModifyConsumer<StudentSubjectRate> {


    private final InstitutionSubjectRateDAO instSubjectRateDAO;

    public StudentSubjectRateModifyConsumer(InstitutionSubjectRateDAO instSubjectRateDAO) {
        this.instSubjectRateDAO = instSubjectRateDAO;
    }

    @Override
    protected void modify(StudentSubjectRate oldItem, StudentSubjectRate newItem) {

        StudentCareerKey studentCareerKey = newItem.getStudentCareerKey();
        CareerKey careerKey = studentCareerKey.getCareerKey();

        String subjectCode = newItem.getSubjectCode();
        String oldRate = Integer.toString(oldItem.getRate());
        String newRate = Integer.toString(newItem.getRate());

        InstitutionSubjectRate subjectRate = instSubjectRateDAO.fetchOrCreate(subjectCode, careerKey);


        subjectRate.getRates().computeIfPresent(oldRate, (key, val) -> val - 1);
        subjectRate.getRates().compute(newRate, (key, val) -> val != null ? val + 1 : 1);

        instSubjectRateDAO.save(subjectRate);
    }

    @Override
    protected Class<StudentSubjectRate> itemClass() {
        return StudentSubjectRate.class;
    }
}
