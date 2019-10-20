package app.universy.course.rate;

import app.universy.course.rate.dao.institution.course.rate.InstitutionCourseRateDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StudentCourseRateDynamoHandlerTest {

    private InstitutionCourseRateDAO institutionCourseRateDAO;
    private StudentCourseRateDynamoHandler handler;

    @Before
    public void setUp() {
        institutionCourseRateDAO = Mockito.mock(InstitutionCourseRateDAO.class);
        handler = new StudentCourseRateDynamoHandler(institutionCourseRateDAO);
    }

    @Test
    public void testReturnedTypes() {
        //assertEquals(InstitutionSubjectRateRequest.class, handler.getInputClass());
        //assertEquals(InstitutionSubjectRateGetFunction.class, handler.getFunction().getClass());
    }
}