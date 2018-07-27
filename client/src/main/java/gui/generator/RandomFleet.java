package gui.generator;


import com.google.gson.Gson;
import gui.printers.FleetView;
import gui.printers.ShipView;

/**
 * GUI-side fleet random generator
 */
public class RandomFleet {

    public final FleetView getGUIFleet(String jsonFleet) {
        ShipView[] arrayOfShips = createArrayOfShipsFromGson(jsonFleet);
        return createFleetViewFromArray(arrayOfShips);
    }

    private ShipView[] createArrayOfShipsFromGson(String outputFromServer) {
        Gson gson = new Gson();
        return gson.fromJson(outputFromServer, ShipView[].class);
    }

    private FleetView createFleetViewFromArray(ShipView[] arrayOfShips) {
        FleetView fleetView = new FleetView();
        for (ShipView ship:arrayOfShips) {
            fleetView.add(ship);
        }
        return fleetView;
    }
}
