package app.universy.subject.rate;

import app.universy.lambda.handlers.dynamo.DynamoDBStreamHandler;
import app.universy.subject.rate.dao.institution.subject.rate.InstitutionSubjectRateDAO;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import app.universy.subject.rate.consumer.StudentSubjectRateInsertConsumer;
import app.universy.subject.rate.consumer.StudentSubjectRateModifyConsumer;
import app.universy.subject.rate.dao.institution.subject.rate.DynamoInstitutionSubjectRateDAO;
import app.universy.subject.rate.model.student.StudentSubjectRate;

import java.util.function.Consumer;

public class StudentSubjectRateDynamoHandler extends DynamoDBStreamHandler<StudentSubjectRate> {

    private final InstitutionSubjectRateDAO institutionSubjectRateDAO;

    public StudentSubjectRateDynamoHandler() {
        this.institutionSubjectRateDAO = new DynamoInstitutionSubjectRateDAO();
    }

    public StudentSubjectRateDynamoHandler(InstitutionSubjectRateDAO institutionSubjectRateDAO) {
        this.institutionSubjectRateDAO = institutionSubjectRateDAO;
    }

    @Override
    protected Consumer<? super StreamRecord> getInsertConsumer() {
        return new StudentSubjectRateInsertConsumer(institutionSubjectRateDAO);
    }

    @Override
    protected Consumer<? super StreamRecord> getModifyConsumer() {
        return new StudentSubjectRateModifyConsumer(institutionSubjectRateDAO);
    }
}