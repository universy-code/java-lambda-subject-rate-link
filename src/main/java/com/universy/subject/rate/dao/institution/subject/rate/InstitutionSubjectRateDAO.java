package com.universy.subject.rate.dao.institution.subject.rate;

import com.universy.subject.rate.model.institution.CareerKey;
import com.universy.subject.rate.model.institution.InstitutionSubjectRate;

public interface InstitutionSubjectRateDAO {
    InstitutionSubjectRate fetchOrCreate(String subjectCode, CareerKey careerKey);
    void save(InstitutionSubjectRate institutionSubjectRate);
}
