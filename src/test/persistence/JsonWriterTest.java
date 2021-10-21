// Got most code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.Homework;
import model.HomeworkAgenda;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            HomeworkAgenda hwa = new HomeworkAgenda();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            HomeworkAgenda hwa = new HomeworkAgenda();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(hwa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            hwa = reader.read();
            assertEquals(0, hwa.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            HomeworkAgenda hwa = new HomeworkAgenda();
            hwa.addHomework(new Homework("math", "w1"));
            hwa.addHomework(new Homework("eng", "r1"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(hwa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            hwa = reader.read();
            List<Homework> homeworkAgenda = hwa.getAgenda();
            checkHomework("math", "w1", homeworkAgenda.get(0));
            checkHomework("eng", "r1", homeworkAgenda.get(1));
            assertEquals(2, homeworkAgenda.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}