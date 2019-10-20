package app.universy.course.rate.model.institution;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CareerKey {

    private String institutionKey;
    private String careerCode;

    public CareerKey() {
    }

    public CareerKey(@JsonProperty(value = "institutionKey", required = true) String institutionKey,
                     @JsonProperty(value = "careerCode", required = true) String careerCode) {
        this.institutionKey = institutionKey;
        this.careerCode = careerCode;
    }

    public String getInstitutionKey() {
        return institutionKey;
    }

    public void setInstitutionKey(String institutionKey) {
        this.institutionKey = institutionKey;
    }

    public String getCareerCode() {
        return careerCode;
    }

    public void setCareerCode(String careerCode) {
        this.careerCode = careerCode;
    }

    @Override
    public String toString() {
        return "CareerKey{" +
                "institutionKey='" + institutionKey + '\'' +
                ", careerCode='" + careerCode + '\'' +
                '}';
    }
}
