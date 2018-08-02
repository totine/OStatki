package connection.commands;

import com.google.gson.JsonObject;
import connection.ServerConnection;
import gui.printers.FleetView;
import gui.receivers.RandomFleet;
import gui.utility.JSONConverter;

public class SendFleetCommand implements CommandFromServer {
    private FleetView fleetView ;
    private ServerConnection serverConnection;

    public SendFleetCommand(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public void execute() {
        serverConnection.addFleetToQueue(fleetView);
    }

    @Override
    public void setValue(JsonObject value) {
        System.out.println(value);

        fleetView = JSONConverter.convertToClass(value.toString(), FleetView.class);
        System.out.println(fleetView);

            }

    public static void main(String[] args) {
        String fleetstr = "{\"shipList\":[{\"mastCoordinates\":[{\"x\":6,\"y\":3},{\"x\":6,\"y\":2},{\"x\":6,\"y\":1},{\"x\":6,\"y\":0}]},{\"mastCoordinates\":[{\"x\":0,\"y\":5},{\"x\":0,\"y\":4},{\"x\":0,\"y\":3}]},{\"mastCoordinates\":[{\"x\":3,\"y\":1},{\"x\":2,\"y\":1},{\"x\":1,\"y\":1}]},{\"mastCoordinates\":[{\"x\":4,\"y\":4},{\"x\":4,\"y\":5}]},{\"mastCoordinates\":[{\"x\":8,\"y\":9},{\"x\":8,\"y\":8}]},{\"mastCoordinates\":[{\"x\":4,\"y\":9},{\"x\":5,\"y\":9}]},{\"mastCoordinates\":[{\"x\":6,\"y\":6}]},{\"mastCoordinates\":[{\"x\":9,\"y\":6}]},{\"mastCoordinates\":[{\"x\":8,\"y\":1}]},{\"mastCoordinates\":[{\"x\":9,\"y\":4}]}]}";
        FleetView flet = JSONConverter.convertToClass(fleetstr, FleetView.class);
        System.out.println(flet);
    }
}
