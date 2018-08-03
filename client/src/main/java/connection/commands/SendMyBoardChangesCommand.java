package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.controllers.GameSceneController;
import gui.data.FieldBus;
import gui.instance.ClientAppRunner;
import gui.receivers.ShotResult;
import gui.utility.ShotBoardHandler;
import javafx.scene.layout.GridPane;

public class SendMyBoardChangesCommand implements CommandFromServer {
    private FieldBus fieldBus;
    private ServerConnection serverConnection;

    SendMyBoardChangesCommand(ServerConnection queuesHandler) {
        serverConnection = queuesHandler;
    }

    @Override
    public void execute() {
        serverConnection.addMyBoardChangesQueue(fieldBus);

    }

    @Override
    public void setValue(JsonObject value) {
        fieldBus = ShotResult.getShotInformation(value.get("results").toString());
    }
}
