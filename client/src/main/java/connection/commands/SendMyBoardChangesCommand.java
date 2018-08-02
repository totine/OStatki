package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.FieldBus;

public class SendMyBoardChangesCommand implements CommandFromServer {
    private FieldBus fieldBus;
    private ServerConnection serverConnection;

    public SendMyBoardChangesCommand(ServerConnection queuesHandler) {
        serverConnection = queuesHandler;
    }

    @Override
    public void execute() {
        serverConnection.addMyBoardChangesQueue(fieldBus);
    }

    @Override
    public void setValue(JsonObject value) {
        System.out.println(value);
    }
}
