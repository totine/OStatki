package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.Player;
import gui.utility.JSONConverter;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class CurrentPlayerCommand implements CommandFromServer {
    private final ServerConnection serverConnection;
    private Player currentPlayer;
    private InvalidationListener listener;
    private Observable observable;

    CurrentPlayerCommand(ServerConnection clientIO, InvalidationListener listener, Observable observable) {
        this.serverConnection = clientIO;
        this.listener = listener;
        this.observable = observable;
    }

    CurrentPlayerCommand(ServerConnection clientIO) {
        this.serverConnection = clientIO;
    }

    @Override
    public void execute() {
        serverConnection.setCurrentPlayer(currentPlayer);
        invalidateIfNotNull();
    }

    private void invalidateIfNotNull() {
        if ((null != listener) && (null != observable)) {
            listener.invalidated(observable);
        }
    }

    @Override
    public void setValue(JsonObject value) {
        this.currentPlayer = JSONConverter.convertToClass(value.toString(), Player.class);
    }
}
