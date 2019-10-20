package app.universy.course.rate.dao.institution.course.rate;

import app.universy.course.rate.model.institution.InstitutionCourseRate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.universy.common.dynamo.DynamoDBMapperFactory;

import java.util.Optional;

public class DynamoInstitutionCourseRateDAO implements InstitutionCourseRateDAO {

    private final DynamoDBMapper dynamoDBMapper;

    public DynamoInstitutionCourseRateDAO(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public DynamoInstitutionCourseRateDAO() {
        this(DynamoDBMapperFactory.createMapper());
    }

    public Optional<InstitutionCourseRate> fetch(String courseCode) {
        return Optional.ofNullable(dynamoDBMapper.load(InstitutionCourseRate.class, courseCode));
    }

    public void save(InstitutionCourseRate institutionCourseRate) {
        dynamoDBMapper.save(institutionCourseRate);
    }
}
