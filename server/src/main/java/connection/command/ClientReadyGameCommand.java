package connection.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;
import connection.serializers.JSONConverter;
import model.preparing.Player;

import java.lang.reflect.Type;

public class ClientReadyGameCommand implements GameCommand {
    private final QueuesHandler queuesHandler;
    Player player;

    public ClientReadyGameCommand(QueuesHandler queuesHandler) {
        this.queuesHandler = queuesHandler;
    }

    @Override
    public void execute() {
        queuesHandler.setReady(player);
    }

    @Override
    public void setValue(JsonObject value) {
        Gson gson = new Gson();
        this.player = gson.fromJson(value, Player.class);
    }
}
