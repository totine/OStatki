package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.printers.FleetView;
import gui.utility.JSONConverter;

public class SendFleetCommand implements CommandFromServer {
    private FleetView fleetView;
    private ServerConnection serverConnection;

    SendFleetCommand(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public void execute() {
        serverConnection.addFleetToQueue(fleetView);
    }

    @Override
    public void setValue(JsonObject value) {
        fleetView = JSONConverter.convertToClass(value.toString(), FleetView.class);
    }

}
