//// Got most code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.Event;
import model.EventLog;
import model.Homework;

import model.HomeworkAgenda;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads HomeworkAgenda from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads HomeworkAgenda from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HomeworkAgenda read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHomeworkAgenda(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses HomeworkAgenda from JSON object and returns it
    private HomeworkAgenda parseHomeworkAgenda(JSONObject jsonObject) {
        HomeworkAgenda hwa = new HomeworkAgenda();
        addAgenda(hwa, jsonObject);
        return hwa;
    }

    // MODIFIES: hwa
    // EFFECTS: parses agenda from JSON object and adds them to HomeworkAgenda
    private void addAgenda(HomeworkAgenda hwa, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("agenda");
        for (Object json : jsonArray) {
            JSONObject nextHomework = (JSONObject) json;
            addHomework(hwa, nextHomework);
        }
    }

    // MODIFIES: hwa
    // EFFECTS: parses homework from JSON object and adds it to HomeworkAgenda
    private void addHomework(HomeworkAgenda hwa, JSONObject jsonObject) {
        String subject = jsonObject.getString("subject");
        String description = jsonObject.getString("description");
        Homework homework = new Homework(subject, description);
        hwa.addHomework(homework);
    }
}
