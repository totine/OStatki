package connection.commands;

import com.google.gson.JsonObject;

public interface CommandFromServer {
    void execute();
    void setValue(JsonObject value);
}
