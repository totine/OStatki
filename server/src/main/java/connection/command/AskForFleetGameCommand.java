package connection.command;

import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;
import connection.serializers.FleetSerializer;
import connection.serializers.JSONConverter;
import connection.utility.Command;
import connection.utility.CommandType;
import game.placement.FleetController;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

public class AskForFleetGameCommand implements GameCommand {
    private final QueuesHandler communicationRun;
    private Fleet<PlacedShip> fleetToSend;

    AskForFleetGameCommand(QueuesHandler communicationRun) {
        this.communicationRun = communicationRun;
    }

    @Override
    public void execute() {
        String serializedFleet = FleetSerializer.from(fleetToSend).serialize();
        communicationRun.sendMessage(serializedFleet);
    }


    @Override
    public void setValue(JsonObject value) {
        fleetToSend = FleetController.generatePlacedStandardFleet();
    }
}
