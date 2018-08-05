package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.Player;
import gui.utility.JSONConverter;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class EndGameCommand implements CommandFromServer {
    private final ServerConnection serverConnection;
    private Observable observable;
    private Player winner;
    private InvalidationListener listener;

    EndGameCommand(ServerConnection clientIO, InvalidationListener listener, Observable observable) {
        this.serverConnection = clientIO;
        this.listener = listener;
        this.observable = observable;
    }

    EndGameCommand(ServerConnection clientIO) {
        this.serverConnection = clientIO;
    }

    @Override
    public void execute() {
        serverConnection.setWinner(winner);
        serverConnection.setGameIsEnd();
        listener.invalidated(observable);
    }

    @Override
    public void setValue(JsonObject value) {
        winner = JSONConverter.convertToClass(value.toString(), Player.class);
    }
}
