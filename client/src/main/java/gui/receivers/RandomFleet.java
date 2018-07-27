package gui.receivers;


import gui.printers.FleetView;
import gui.printers.ShipView;
import gui.utility.JSONConverter;

/**
 * GUI-side fleet random receivers
 */
public class RandomFleet {

    public final FleetView getGUIFleet(String jsonFleet) {
        ShipView[] arrayOfShips = createArrayOfShipsFromGson(jsonFleet);
        return createFleetViewFromArray(arrayOfShips);
    }

    private ShipView[] createArrayOfShipsFromGson(String outputFromServer) {
        return JSONConverter.convertToClass(outputFromServer, ShipView[].class);
    }

    private FleetView createFleetViewFromArray(ShipView[] arrayOfShips) {
        FleetView fleetView = new FleetView();
        for (ShipView ship : arrayOfShips) {
            fleetView.add(ship);
        }
        return fleetView;
    }
}
