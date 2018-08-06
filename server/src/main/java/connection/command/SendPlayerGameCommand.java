package connection.command;


import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;
import model.preparing.Player;

public class SendPlayerGameCommand implements GameCommand {
    private Player player;
    private final QueuesHandler communicationRun;

    SendPlayerGameCommand(QueuesHandler communicationRun) {
        this.communicationRun = communicationRun;
    }

    @Override
    public void execute() {
        try {
            communicationRun.addPlayerToQueue(player);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setValue(JsonObject value) {
        value.get("name");
        String name = value.get("name").toString();
        int id = Integer.parseInt(value.get("id").toString());
        player = Player.create(name, id);
    }

}
