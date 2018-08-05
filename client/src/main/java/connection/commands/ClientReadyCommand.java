package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.data.Player;
import gui.utility.JSONConverter;

public class ClientReadyCommand implements CommandFromServer {
    private Player currentPlayer;
    private ServerConnection serverConnection;

    ClientReadyCommand(ServerConnection clientIO) {
        this.serverConnection = clientIO;
    }

    @Override
    public void execute() {

    }

    @Override
    public void setValue(JsonObject value) {
        this.currentPlayer = JSONConverter.convertToClass(value.toString(), Player.class);
    }
}
