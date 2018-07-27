package connection;

import controller.FleetController;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

public class AskForFleetGameCommand implements GameCommand {
    private final Server server;

    AskForFleetGameCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String message) {
        Fleet<PlacedShip> fleet = FleetController.generatePlacedStandardFleet();
        String serializedFleet = FleetSerializer.from(fleet).serialize();
        server.sendMessageToAll(serializedFleet);
    }
}
