package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.Player;
import gui.utility.JSONConverter;

public class EndGameCommand implements CommandFromServer {
    private final ServerConnection serverConnection;
    private Player winner;

    EndGameCommand(ServerConnection clientIO) {
        this.serverConnection = clientIO;
    }

    @Override
    public void execute() {
        serverConnection.setWinner(winner);
        serverConnection.setGameIsEnd();
    }

    @Override
    public void setValue(JsonObject value) {
        winner = JSONConverter.convertToClass(value.toString(), Player.class);
    }
}
