package connection.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import game.placement.FleetController;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

import java.lang.reflect.Type;

public class JSONConverter {
    private static Gson convertHandler = new GsonBuilder().enableComplexMapKeySerialization().create();

    private JSONConverter() {
    }

    public static <T> T convertToClass(String input, Type type, T object) {
        return convertHandler.fromJson(input, type);
    }

    public static <T> String convertToJSON(T classToConvert) {
        return convertHandler.toJson(classToConvert);
    }

    public static void main(String[] args) {
        Fleet<PlacedShip> fleet = FleetController.generatePlacedStandardFleet();
        String json = JSONConverter.convertToJSON(fleet);
        Fleet<PlacedShip> fleet1 = JSONConverter.convertToClass(json, Fleet.class, new Fleet<PlacedShip>());

        System.out.println(json);
        System.out.println(fleet1);
    }
}
