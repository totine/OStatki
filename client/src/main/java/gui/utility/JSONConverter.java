package gui.utility;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JSONConverter {
    private static Gson convertHandler = new Gson();

    private JSONConverter() {
    }

    public static <T> T convertToClass(String input, Type type) {
        return convertHandler.fromJson(input, type);
    }

    public static <T> String convertToJSON(T classToConvert) {
        return convertHandler.toJson(classToConvert);
    }
}
