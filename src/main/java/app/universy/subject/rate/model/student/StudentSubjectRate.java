package app.universy.subject.rate.model.student;

import app.universy.subject.rate.converters.StudentCareerKeyDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class StudentSubjectRate {

    @JsonDeserialize(using = StudentCareerKeyDeserializer.class)
    private StudentCareerKey studentCareerKey;

    private String subjectCode;

    private int rate;

    public StudentSubjectRate() {
    }

    public StudentSubjectRate(@JsonProperty(value = "studentCareerKey", required = true) StudentCareerKey studentCareerKey,
                              @JsonProperty(value = "subjectCode", required = true) String subjectCode,
                              @JsonProperty(value = "rate", required = true) int rate) {
        this.studentCareerKey = studentCareerKey;
        this.subjectCode = subjectCode;
        this.rate = rate;
    }

    public StudentCareerKey getStudentCareerKey() {
        return studentCareerKey;
    }

    public void setStudentCareerKey(StudentCareerKey studentCareerKey) {
        this.studentCareerKey = studentCareerKey;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
