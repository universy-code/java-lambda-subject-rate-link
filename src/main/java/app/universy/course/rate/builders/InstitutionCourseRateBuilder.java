package app.universy.course.rate.builders;

import app.universy.course.rate.model.institution.CareerKey;
import app.universy.course.rate.model.institution.InstitutionCourseRate;

public class InstitutionCourseRateBuilder {

    private String courseCode;
    private CareerKey careerKey;

    public InstitutionCourseRateBuilder withCourseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    public InstitutionCourseRateBuilder withCareerKey(CareerKey careerKey) {
        this.careerKey = careerKey;
        return this;
    }

    public InstitutionCourseRate build() {
        return new InstitutionCourseRate(courseCode, careerKey);
    }

}
