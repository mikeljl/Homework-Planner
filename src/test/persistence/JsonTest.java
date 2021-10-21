// Got most code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

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
