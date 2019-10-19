package com.universy.subject.rate.model.institution;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDelimited;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.HashMap;
import java.util.Map;

@DynamoDBTable(tableName = "institution-subject-rate")
public class InstitutionSubjectRate {

    @DynamoDBHashKey
    private String subjectCode;

    @DynamoDBAttribute
    private Map<String, Integer> rates;

    @DynamoDBAttribute
    @DynamoDBDelimited(attributeNames = {"institutionKey", "careerCode"}, delimiter = '_')
    private CareerKey careerKey;

    public InstitutionSubjectRate(String subjectCode, CareerKey careerKey) {
        this.careerKey = careerKey;
        this.subjectCode = subjectCode;
        this.rates = new HashMap<>();
    }

    public InstitutionSubjectRate() {
    }

    public CareerKey getCareerKey() {
        return careerKey;
    }

    public void setCareerKey(CareerKey careerKey) {
        this.careerKey = careerKey;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Map<String, Integer> getRates() {
        return rates;
    }

    public void setRates(Map<String, Integer> rates) {
        this.rates = rates;
    }
}
