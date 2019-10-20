package app.universy.course.rate.model.student;

import app.universy.course.rate.model.institution.CareerKey;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentCareerKey {

    private String username;
    private String institutionKey;
    private String careerCode;

    public StudentCareerKey(@JsonProperty(value = "username", required = true) String username,
                            @JsonProperty(value = "institutionKey", required = true) String institutionKey,
                            @JsonProperty(value = "careerCode", required = true) String careerCode) {
        this.username = username;
        this.institutionKey = institutionKey;
        this.careerCode = careerCode;
    }

    public String getUsername() {
        return username;
    }

    public CareerKey getCareerKey() {
        return new CareerKey(institutionKey, careerCode);
    }

    @Override
    public String toString() {
        return "StudentCareerKey{" +
                "username='" + username + '\'' +
                ", institutionKey='" + institutionKey + '\'' +
                ", careerCode='" + careerCode + '\'' +
                '}';
    }
}
