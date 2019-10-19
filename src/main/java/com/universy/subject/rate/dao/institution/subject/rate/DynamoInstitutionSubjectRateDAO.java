package com.universy.subject.rate.dao.institution.subject.rate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.universy.common.dynamo.DynamoDBMapperFactory;
import com.universy.subject.rate.model.institution.CareerKey;
import com.universy.subject.rate.model.institution.InstitutionSubjectRate;

import java.util.Optional;

public class DynamoInstitutionSubjectRateDAO implements InstitutionSubjectRateDAO {

    private final DynamoDBMapper dynamoDBMapper;

    public DynamoInstitutionSubjectRateDAO(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public DynamoInstitutionSubjectRateDAO() {
        this(DynamoDBMapperFactory.createMapper());
    }

    public InstitutionSubjectRate fetchOrCreate(String subjectCode, CareerKey careerKey) {
        return Optional.ofNullable(dynamoDBMapper.load(InstitutionSubjectRate.class, subjectCode))
                .orElse(new InstitutionSubjectRate(subjectCode, careerKey));
    }

    public void save(InstitutionSubjectRate institutionSubjectRate) {
        dynamoDBMapper.save(institutionSubjectRate);
    }
}
