package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Homework having a subject, due date, and a description
public class Homework implements Writable {
    private String subject;
    private String description;

    // EFFECTS: homework has given subject, due date and description
    public Homework(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    // EFFECTS: returns subject of homework
    public String getSubject() {
        return subject;
    }

    // EFFECTS: returns description of homework
    public String getDescription() {
        return description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subject", subject);
        json.put("description", description);
        return json;
    }
}
