package connection.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import game.placement.FleetController;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;
import model.preparing.Player;

import java.lang.reflect.Type;

public class JSONConverter {
    private static Gson convertHandler = new GsonBuilder().enableComplexMapKeySerialization().create();

    private JSONConverter() {
    }

    public static <T> T convertToClass(String input, Type type) {
        return convertHandler.fromJson(input, type);
    }

    public static <T> String convertToJSON(T classToConvert) {
        return convertHandler.toJson(classToConvert);
    }

    public static void main(String[] args) {

        Player player = Player.create("Roman");
        String jsonPlayer = JSONConverter.convertToJSON(player);
        Player afterConversionPlayer = JSONConverter.convertToClass(jsonPlayer, Player.class);

        System.out.println("This is the player before conversion: "+player);
        System.out.println("This is the player before conversion: "+afterConversionPlayer);

    }
}
