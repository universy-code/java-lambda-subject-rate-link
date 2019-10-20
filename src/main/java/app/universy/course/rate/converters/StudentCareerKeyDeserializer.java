package app.universy.course.rate.converters;


import app.universy.course.rate.converters.exceptions.StudentCareerKeyCorruptException;
import app.universy.course.rate.model.student.StudentCareerKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StudentCareerKeyDeserializer extends JsonDeserializer<StudentCareerKey> {

    private static final String USERNAME_SEPARATOR = "@";
    private static final String CAREER_KEY_SEPARATOR = "_";
    private static final int KEY_PART_COUNT = 3;
    private static final int USERNAME = 0;
    private static final int INSTITUTION_KEY = 1;
    private static final int CAREER_CODE = 2;

    @Override
    public StudentCareerKey deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String studentCareerKey = jsonParser.readValueAs(String.class);

        String pattenToSplit = String.format("(%s|%s)", USERNAME_SEPARATOR, CAREER_KEY_SEPARATOR);

        String[] keyParts = studentCareerKey.split(pattenToSplit);

        if (keyParts.length != KEY_PART_COUNT) {
            throw new StudentCareerKeyCorruptException();
        }

        String username = keyParts[USERNAME];
        String institutionKey = keyParts[INSTITUTION_KEY];
        String careerCode = keyParts[CAREER_CODE];

        return new StudentCareerKey(username, institutionKey, careerCode);
    }
}