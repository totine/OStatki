package connection.command;

import com.google.gson.JsonObject;

public interface GameCommand {
    void execute();

    void setValue(JsonObject value);
}
