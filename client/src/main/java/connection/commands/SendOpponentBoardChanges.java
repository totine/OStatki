package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.FieldBus;

public class SendOpponentBoardChanges implements CommandFromServer {
    private FieldBus fieldBus;
    private ServerConnection serverConnection;

    public SendOpponentBoardChanges(ServerConnection queuesHandler) {
        serverConnection = queuesHandler;
    }

    @Override
    public void execute() {
        serverConnection.addOpponentBoardChangesQueue(fieldBus);
    }

    @Override
    public void setValue(JsonObject value) {
        System.out.println(value);
    }
}
