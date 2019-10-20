package app.universy.course.rate.dao.institution.course.rate;

import app.universy.course.rate.model.institution.InstitutionCourseRate;

import java.util.Optional;

public interface InstitutionCourseRateDAO {
    Optional<InstitutionCourseRate> fetch(String courseCode);

    void save(InstitutionCourseRate institutionCourseRate);
}
