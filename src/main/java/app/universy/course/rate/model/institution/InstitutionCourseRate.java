package app.universy.course.rate.model.institution;

import app.universy.course.rate.model.institution.tag.TagRate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDelimited;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "institution-course-rate")
public class InstitutionCourseRate {

    @DynamoDBHashKey
    private String courseCode;

    @DynamoDBAttribute
    private String subjectCode;

    @DynamoDBAttribute
    private Map<String, Integer> overall;

    @DynamoDBAttribute
    private Map<String, Integer> difficulty;

    @DynamoDBAttribute
    private int wouldTakeAgain;

    @DynamoDBAttribute
    private int wouldNotTakeAgain;

    @DynamoDBAttribute
    private List<TagRate> tagsRate;

    @DynamoDBAttribute
    @DynamoDBDelimited(attributeNames = {"institutionKey", "careerCode"}, delimiter = '_')
    private CareerKey careerKey;

    public InstitutionCourseRate() {
    }

    public InstitutionCourseRate(String courseCode, CareerKey careerKey) {
        this.courseCode = courseCode;
        this.overall = new HashMap<>();
        this.difficulty = new HashMap<>();
        this.wouldNotTakeAgain = 0;
        this.wouldTakeAgain = 0;
        this.tagsRate = new ArrayList<>();
        this.careerKey = careerKey;
    }

    public void incrementWouldTakeAgain() {
        this.wouldTakeAgain++;
    }

    public void decrementWouldTakeAgain() {
        this.wouldTakeAgain--;
    }

    public void incrementWouldNotTakeAgain() {
        this.wouldNotTakeAgain++;
    }

    public void decrementWouldNotTakeAgain() {
        this.wouldNotTakeAgain++;
    }


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Map<String, Integer> getOverall() {
        return overall;
    }

    public void setOverall(Map<String, Integer> overall) {
        this.overall = overall;
    }

    public Map<String, Integer> getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Map<String, Integer> difficulty) {
        this.difficulty = difficulty;
    }

    public int getWouldTakeAgain() {
        return wouldTakeAgain;
    }

    public void setWouldTakeAgain(int wouldTakeAgain) {
        this.wouldTakeAgain = wouldTakeAgain;
    }

    public int getWouldNotTakeAgain() {
        return wouldNotTakeAgain;
    }

    public void setWouldNotTakeAgain(int wouldNotTakeAgain) {
        this.wouldNotTakeAgain = wouldNotTakeAgain;
    }

    public List<TagRate> getTagsRate() {
        return tagsRate;
    }

    public void setTagsRate(List<TagRate> tagsRate) {
        this.tagsRate = tagsRate;
    }

    public CareerKey getCareerKey() {
        return careerKey;
    }

    public void setCareerKey(CareerKey careerKey) {
        this.careerKey = careerKey;
    }
}
