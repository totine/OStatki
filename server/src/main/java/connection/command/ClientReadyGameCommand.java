package connection.command;

import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;
import connection.serializers.JSONConverter;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

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
        fleetFromPlayer = JSONConverter.convertToClass(value.toString(), Fleet.class, new Fleet<>());
    }
}
