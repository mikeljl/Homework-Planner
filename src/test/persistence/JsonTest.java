package persistence;

import model.Homework;
import model.HomeworkAgenda;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkHomework(String subject, String description, Homework homework) {
        assertEquals(subject, homework.getSubject());
        assertEquals(description, homework.getDescription());
    }
}
