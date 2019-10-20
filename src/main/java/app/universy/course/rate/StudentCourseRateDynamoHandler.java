package app.universy.course.rate;

import app.universy.course.rate.consumer.StudentCourseRateInsertConsumer;
import app.universy.course.rate.consumer.StudentCourseRateModifyConsumer;
import app.universy.course.rate.dao.institution.course.rate.DynamoInstitutionCourseRateDAO;
import app.universy.course.rate.dao.institution.course.rate.InstitutionCourseRateDAO;
import app.universy.course.rate.model.student.StudentCourseRate;
import app.universy.lambda.handlers.dynamo.DynamoDBStreamHandler;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;

import java.util.function.Consumer;

public class StudentCourseRateDynamoHandler extends DynamoDBStreamHandler<StudentCourseRate> {

    private final InstitutionCourseRateDAO institutionCourseRateDAO;

    public StudentCourseRateDynamoHandler() {
        this.institutionCourseRateDAO = new DynamoInstitutionCourseRateDAO();
    }

    public StudentCourseRateDynamoHandler(InstitutionCourseRateDAO institutionCourseRateDAO) {
        this.institutionCourseRateDAO = institutionCourseRateDAO;
    }

    @Override
    protected Consumer<? super StreamRecord> getInsertConsumer() {
        return new StudentCourseRateInsertConsumer(institutionCourseRateDAO);
    }

    @Override
    protected Consumer<? super StreamRecord> getModifyConsumer() {
        return new StudentCourseRateModifyConsumer(institutionCourseRateDAO);
    }
}