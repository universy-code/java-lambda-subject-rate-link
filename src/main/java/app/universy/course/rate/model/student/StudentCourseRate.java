package app.universy.course.rate.model.student;

import app.universy.course.rate.converters.StudentCareerKeyDeserializer;
import app.universy.course.rate.model.institution.tag.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class StudentCourseRate {

    @JsonDeserialize(using = StudentCareerKeyDeserializer.class)
    private StudentCareerKey studentCareerKey;

    private String courseCode;

    private int overall;

    private int difficulty;

    private boolean wouldTakeAgain;

    private List<Tag> tags;

    public StudentCourseRate() {
    }

    public StudentCourseRate(@JsonProperty(value = "studentCareerKey", required = true) StudentCareerKey studentCareerKey,
                             @JsonProperty(value = "courseCode", required = true) String courseCode,
                             @JsonProperty(value = "overall", required = true) int overall,
                             @JsonProperty(value = "difficulty", required = true) int difficulty,
                             @JsonProperty(value = "wouldTakeAgain", required = true) boolean wouldTakeAgain,
                             @JsonProperty(value = "tags", required = true) List<Tag> tags) {

        this.studentCareerKey = studentCareerKey;
        this.courseCode = courseCode;
        this.overall = overall;
        this.difficulty = difficulty;
        this.wouldTakeAgain = wouldTakeAgain;
        this.tags = tags;
    }

    public StudentCareerKey getStudentCareerKey() {
        return studentCareerKey;
    }

    public void setStudentCareerKey(StudentCareerKey studentCareerKey) {
        this.studentCareerKey = studentCareerKey;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean wouldTakeAgain() {
        return wouldTakeAgain;
    }

    public void setWouldTakeAgain(boolean wouldTakeAgain) {
        this.wouldTakeAgain = wouldTakeAgain;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
