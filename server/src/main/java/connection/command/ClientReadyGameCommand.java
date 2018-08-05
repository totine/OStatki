package connection.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;
import model.preparing.Player;

public class ClientReadyGameCommand implements GameCommand {
    private final QueuesHandler queuesHandler;
    private Player player;

    ClientReadyGameCommand(QueuesHandler queuesHandler) {
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
