package com.universy.subject.rate;

import com.universy.subject.rate.dao.institution.subject.rate.InstitutionSubjectRateDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StudentSubjectRateDynamoHandlerTest {

    private InstitutionSubjectRateDAO institutionSubjectRateDAO;
    private StudentSubjectRateDynamoHandler handler;

    @Before
    public void setUp() {
        institutionSubjectRateDAO = Mockito.mock(InstitutionSubjectRateDAO.class);
        handler = new StudentSubjectRateDynamoHandler(institutionSubjectRateDAO);
    }

    @Test
    public void testReturnedTypes() {
        //assertEquals(InstitutionSubjectRateRequest.class, handler.getInputClass());
        //assertEquals(InstitutionSubjectRateGetFunction.class, handler.getFunction().getClass());
    }
}