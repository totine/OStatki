package connection.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import connection.communication.QueuesHandler;
import connection.serializers.FleetSerializer;
import connection.serializers.JSONConverter;
import connection.utility.Command;
import connection.utility.CommandType;
import game.placement.FleetController;
import model.Coordinates;
import model.placement.fleet.Fleet;
import model.placement.fleet.FleetBuilder;
import model.placement.ship.Direction;
import model.placement.ship.PlacedShip;
import model.placement.ship.Ship;
import model.placement.ship.ShipType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    }


    @Override
    public void setValue(JsonObject value) {

        fleetToSend = FleetController.generatePlacedStandardFleet();
        fleetToSend = FleetBuilder.create().appendShip(Coordinates.create(1, 1), ShipType.TWO_MAST, Direction.SOUTH).build();
    }

    public static void main(String[] args) {
        Fleet<PlacedShip> fleet = FleetController.generatePlacedStandardFleet();
        Command command = Command.withType(CommandType.SEND_FLEET, fleet);
        String s = JSONConverter.convertToJSON(command);
        JsonParser parser = new JsonParser();
        JsonElement parse = parser.parse(s);
        JsonObject o = parse.getAsJsonObject();
        JsonElement value = o.get("value");
        JsonObject v = value.getAsJsonObject();
        System.out.println(v.get("shipList"));

        Type type = new TypeToken<Fleet<PlacedShip>> () {}.getType();

        Fleet<PlacedShip> shipFleet = JSONConverter.convertToClass(v.toString(), type, new Fleet<>());
        shipFleet.getShipList().forEach(x -> System.out.println(x));

    }
}
