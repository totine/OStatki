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

        Command command = Command.withType(CommandType.SEND_FLEET, fleetToSend);
        String s = JSONConverter.convertToJSON(command);

        communicationRun.sendMessage(s);
        try {
            communicationRun.addFleetToQueue(fleetToSend); // ?
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setValue(JsonObject value) {
        fleetToSend = FleetController.generatePlacedStandardFleet();
    }
}
