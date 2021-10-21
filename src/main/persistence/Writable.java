// Got most code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}