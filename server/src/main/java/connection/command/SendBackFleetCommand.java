package connection.command;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import connection.communication.QueuesHandler;
import connection.serializers.JSONConverter;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

import java.lang.reflect.Type;

public class SendBackFleetCommand implements GameCommand {
    private final QueuesHandler communicationRun;
    private Fleet<PlacedShip> fleetFromPlayer;

    SendBackFleetCommand(QueuesHandler communicationRun) {
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


        Type type = new TypeToken<Fleet<PlacedShip>>() {
        }.getType();

        Fleet<PlacedShip> shipFleet = JSONConverter.convertToClass(value.toString(), type);
        shipFleet.getShipList().forEach(System.out::println);
        fleetFromPlayer = shipFleet;
    }
}
