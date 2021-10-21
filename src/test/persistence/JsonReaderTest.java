// Got most code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.HomeworkAgenda;
import model.Homework;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HomeworkAgenda hwa = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            HomeworkAgenda hwa = reader.read();
            assertEquals(0, hwa.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            HomeworkAgenda hwa = reader.read();
            List<Homework> homeworkAgenda = hwa.getAgenda();
            checkHomework("math", "w1", homeworkAgenda.get(0));
            checkHomework("eng", "r1", homeworkAgenda.get(1));
            assertEquals(2, homeworkAgenda.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

