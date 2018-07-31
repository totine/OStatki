import gui.printers.FleetView;
import gui.receivers.RandomFleet;
import gui.utility.Command;
import gui.utility.CommandType;
import gui.utility.JSONConverter;
import model.Coordinates;
import org.testng.annotations.Test;

public class CommandTest {
    @Test
    public void testShotCommandSerializing() {
        Command coordinatesCommand = Command.withType(CommandType.SHOT,
                Coordinates.create(5, 7));

        String s = JSONConverter.convertToJSON(coordinatesCommand);

        System.out.println(s);
    }

    @Test
    public void testSendFleetSerializing() {
        String serializedFleet = "[{\"mastCoordinates\":[{\"x\":9,\"y\":3},{\"x\":9,\"y\":2},{\"x\":9,\"y\":1}," +
                "{\"x\":9,\"y\":0}]},{\"mastCoordinates\":[{\"x\":9,\"y\":5},{\"x\":9,\"y\":6},{\"x\":9,\"y\":7}]}," +
                "{\"mastCoordinates\":[{\"x\":5,\"y\":3},{\"x\":5,\"y\":4},{\"x\":5,\"y\":5}]}," +
                "{\"mastCoordinates\":[{\"x\":2,\"y\":8},{\"x\":1,\"y\":8}]}," +
                "{\"mastCoordinates\":[{\"x\":3,\"y\":2},{\"x\":3,\"y\":1}]}," +
                "{\"mastCoordinates\":[{\"x\":2,\"y\":4},{\"x\":3,\"y\":4}]}," +
                "{\"mastCoordinates\":[{\"x\":0,\"y\":1}]}," +
                "{\"mastCoordinates\":[{\"x\":7,\"y\":4}]},{\"mastCoordinates\":[{\"x\":5,\"y\":9}]}," +
                "{\"mastCoordinates\":[{\"x\":9,\"y\":9}]}]";

        FleetView guiFleet = new RandomFleet().getGUIFleet(serializedFleet);
        Command fleetViewCommand = Command.withType(CommandType.SEND_FLEET,
                guiFleet);

        System.out.println(JSONConverter.convertToJSON(fleetViewCommand));
    }
}
