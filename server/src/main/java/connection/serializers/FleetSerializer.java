package connection.serializers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

import java.lang.reflect.Type;
import java.util.List;

public class FleetSerializer {
    private final Fleet<PlacedShip> placedShipFleet;
    private static final Type TYPE_TOKEN = new TypeToken<List<PlacedShip>>() {
    }.getType();

    private FleetSerializer(Fleet<PlacedShip> placedShipFleet) {
        this.placedShipFleet = placedShipFleet;
    }

    public static FleetSerializer from(Fleet<PlacedShip> placedShipFleet) {
        return new FleetSerializer(placedShipFleet);
    }

    public String serialize() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<PlacedShip>>() {
        }.getType();

        return gson.toJson(placedShipFleet.getShipList(), type);
    }

    public static List<PlacedShip> deserialize(String fleetJson) {
        Gson gson = new Gson();
        return gson.fromJson(fleetJson, TYPE_TOKEN);
    }
}
