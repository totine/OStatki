package connection.commands;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import connection.ServerConnection;

import java.util.HashMap;
import java.util.Map;

public class CommandFromServerGenerator {
    private final Map<String, CommandFromServer> commandNameMap;


    public CommandFromServerGenerator(ServerConnection clientIO) {
        this.commandNameMap = new HashMap<>();
        commandNameMap.put("SEND_MY_CHANGES", new SendMyBoardChangesCommand(clientIO));
        commandNameMap.put("SEND_OPPONENT_CHANGES", new SendOpponentBoardChanges(clientIO));
        commandNameMap.put("SEND_FLEET", new SendFleetCommand(clientIO));

    }

    public CommandFromServer createCommandFromMessage(String message) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        JsonElement command = jsonObject.get("command");
        JsonObject value = jsonObject.getAsJsonObject("value");
        CommandFromServer gameCommand = commandNameMap.getOrDefault(command.getAsString(), new UnknownCommand());
        gameCommand.setValue(value);
        return gameCommand;
    }
}
