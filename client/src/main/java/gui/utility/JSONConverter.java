package gui.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.printers.FleetView;

import java.lang.reflect.Type;

public class JSONConverter {
    private static Gson convertHandler = new GsonBuilder().enableComplexMapKeySerialization().create();

    private JSONConverter() {
    }

    public static <T> T convertToClass(String input, Type type) {
        System.out.println(input);
        return convertHandler.fromJson(input, type);
    }

    public static <T> String convertToJSON(T objectOfSomeClass) {
        return convertHandler.toJson(objectOfSomeClass);
    }
}
