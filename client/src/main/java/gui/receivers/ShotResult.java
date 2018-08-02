package gui.receivers;

import com.google.gson.reflect.TypeToken;
import connection.ServerConnection;
import gui.data.FieldBus;
import gui.data.FieldState;
import gui.utility.JSONConverter;
import model.Coordinates;

import java.lang.reflect.Type;
import java.util.Map;

public class ShotResult {
    private static final Type TYPE = getMapType();

    private ShotResult() {
    }

    public static FieldBus getShotInformation(String jsonCoordinates) {
        Map<Coordinates, FieldState> resultMap = createMap(jsonCoordinates);
        return FieldBus.create(resultMap);
    }

    private static Map<Coordinates, FieldState> createMap(String outputFromServer) {
        return JSONConverter.convertToClass(outputFromServer, TYPE);
    }

    public static String takeMessageFromServer(ServerConnection serverConnection) {
        return serverConnection.getMessage();
    }

    private static Type getMapType() {
        return new TypeToken<Map<Coordinates, FieldState>>() {
        }.getType();
    }
}
