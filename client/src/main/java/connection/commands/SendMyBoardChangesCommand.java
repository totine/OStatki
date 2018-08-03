package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.FieldBus;
import gui.receivers.ShotResult;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class SendMyBoardChangesCommand implements CommandFromServer {
    private FieldBus fieldBus;
    private ServerConnection serverConnection;
    private InvalidationListener listener;
    private Observable observable;

    SendMyBoardChangesCommand(ServerConnection queuesHandler, InvalidationListener listener, Observable observable) {
        serverConnection = queuesHandler;
        this.listener = listener;
        this.observable = observable;
    }

    SendMyBoardChangesCommand(ServerConnection queuesHandler) {
        serverConnection = queuesHandler;
    }

    @Override
    public void execute() {
        if ((null != listener) && (null != observable)) {
            listener.invalidated(observable);
        }
        serverConnection.addMyBoardChangesQueue(fieldBus);
    }

    @Override
    public void setValue(JsonObject value) {
        fieldBus = ShotResult.getShotInformation(value.get("results").toString());
    }
}
