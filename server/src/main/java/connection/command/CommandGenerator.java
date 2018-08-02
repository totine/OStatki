package connection.command;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;

import java.util.HashMap;
import java.util.Map;

public class CommandGenerator {
    private final Map<String, GameCommand> commandNameMap;
    private QueuesHandler queuesHandler;

    public CommandGenerator(QueuesHandler queuesHandler) {
        this.queuesHandler = queuesHandler;
        this.commandNameMap = new HashMap<>();
        commandNameMap.put("SEND_PLAYER", new SendPlayerGameCommand(queuesHandler));
        commandNameMap.put("ASK_FOR_FLEET", new AskForFleetGameCommand(queuesHandler));
        commandNameMap.put("SEND_FLEET", new ClientReadyGameCommand(queuesHandler));
        commandNameMap.put("SHOT", new ShotCommand(queuesHandler));
        commandNameMap.put("UNKNOWN", new UnknownCommand(queuesHandler));
    }

    public GameCommand createCommandFromMessage(String message) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        JsonElement command = jsonObject.get("command");
        JsonObject value = jsonObject.getAsJsonObject("value");
        GameCommand gameCommand = commandNameMap.getOrDefault(command.getAsString(), new UnknownCommand(queuesHandler));
        gameCommand.setValue(value);
        return gameCommand;
    }
}
