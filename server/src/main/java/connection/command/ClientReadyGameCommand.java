package connection.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import connection.communication.QueuesHandler;
import connection.serializers.JSONConverter;
import connection.utility.Command;
import connection.utility.CommandType;
import game.placement.FleetController;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

import java.lang.reflect.Type;

public class ClientReadyGameCommand implements GameCommand {
    private final QueuesHandler communicationRun;
    private Fleet<PlacedShip> fleetFromPlayer;

    ClientReadyGameCommand(QueuesHandler communicationRun) {
        this.communicationRun = communicationRun;
    }

    @Override
    public void execute() {
        try {
            communicationRun.addToFleetQueue(fleetFromPlayer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setValue(JsonObject value) {


        Type type = new TypeToken<Fleet<PlacedShip>>() {}.getType();

        Fleet<PlacedShip> shipFleet = JSONConverter.convertToClass(value.toString(), type, new Fleet<>());
        shipFleet.getShipList().forEach(x -> System.out.println(x));
        fleetFromPlayer = shipFleet;
    }
}
