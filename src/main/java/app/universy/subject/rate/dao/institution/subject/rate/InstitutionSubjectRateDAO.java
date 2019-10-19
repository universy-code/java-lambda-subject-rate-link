package app.universy.subject.rate.dao.institution.subject.rate;

import app.universy.subject.rate.model.institution.CareerKey;
import app.universy.subject.rate.model.institution.InstitutionSubjectRate;

public interface InstitutionSubjectRateDAO {
    InstitutionSubjectRate fetchOrCreate(String subjectCode, CareerKey careerKey);
    void save(InstitutionSubjectRate institutionSubjectRate);
}
