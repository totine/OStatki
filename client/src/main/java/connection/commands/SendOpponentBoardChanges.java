package connection.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import connection.ServerConnection;
import gui.data.FieldBus;
import gui.receivers.ShotResult;

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

        fieldBus = ShotResult.getShotInformation(value.get("results").toString());
    }

    public static void main(String[] args) {
        String json = "{\"results\":[[{\"x\":7,\"y\":5},\"SEEN\"]]}";
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(json).getAsJsonObject();
        System.out.println(o );
        FieldBus fieldBus = ShotResult.getShotInformation(o .get("results").toString());
        System.out.println(fieldBus);

    }
}
