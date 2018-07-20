package gui;

import placement.controller.FleetController;
import placement.model.Fleet;
import placement.model.ship.PlacedShip;


/**
 * GUI-side fleet random generator
 */
public class RandomFleet implements FleetDAO {
    @Override
    public final FleetView getGUIFleet() {
        Fleet<PlacedShip> fleetFromRandom = FleetController.generatePlacedStandardFleet();
        FleetView fleetView = new FleetView();
        for (PlacedShip ship : fleetFromRandom.getShipList()) {
            ShipView shipView = new ShipView(ship.getMastCoordinates());
            fleetView.add(shipView);
        }
        return fleetView;
    }
}
