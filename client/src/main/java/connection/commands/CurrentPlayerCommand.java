package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.Player;
import gui.utility.JSONConverter;

public class CurrentPlayerCommand implements CommandFromServer{
    private final ServerConnection serverConnection;
    private Player currentPlayer;

    public CurrentPlayerCommand(ServerConnection clientIO) {
        this.serverConnection = clientIO;
    }

    @Override
    public void execute() {
        serverConnection.setCurrentPlayer(currentPlayer);
    }

    @Override
    public void setValue(JsonObject value) {
        JSONConverter.convertToClass(value.toString(), Player.class);
    }
}
