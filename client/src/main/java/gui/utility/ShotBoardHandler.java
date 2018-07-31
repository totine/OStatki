package gui.utility;

import connection.ServerConnection;
import gui.data.FieldBus;
import gui.data.FieldState;
import gui.receivers.ShotResult;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static gui.styles.AvailableStyles.DAMAGED_STYLE;
import static gui.styles.AvailableStyles.DESTROYED_STYLE;
import static gui.styles.AvailableStyles.WATER_STYLE;

public class ShotBoardHandler {

    private ShotBoardHandler() {
    }

    public static void takeReactionFromServer(ServerConnection serverConnection, GridPane gridPane) throws InterruptedException {
        FieldBus fieldBusToChange = ShotResult.getShotInformation(ShotResult.takeMessageFromServer(serverConnection));
        Map<Coordinates, FieldState> fieldStateMap = fieldBusToChange.getFieldStates();
        takeGridPaneFields(gridPane).forEach(field -> changeFieldsAsInFieldBus(fieldStateMap, field));
    }

    private static Coordinates createCoordinatesFromId(Button field) {
        String[] coordinateArray = field.getId().split(":");
        int x = Integer.parseInt(coordinateArray[0]);
        int y = Integer.parseInt(coordinateArray[1]);
        return Coordinates.create(x, y);
    }

    private static List<Button> takeGridPaneFields(GridPane pane) {
        ObservableList<Node> nodes = pane.getChildren().filtered(x -> !x.getClass().equals(Group.class));
        List<Button> buttonList = new ArrayList<>();
        nodes.forEach(node -> buttonList.add((Button) node));
        return buttonList;
    }

    private static void changeFieldsAsInFieldBus(Map<Coordinates, FieldState> fieldStateMap, Button field) {
        Coordinates thisFieldCoordinates = createCoordinatesFromId(field);
        fieldStateMap.forEach((key, value) -> {
            if (key.equals(thisFieldCoordinates)) {
                changeColorFromState(value, field);
            }
        });
    }

    private static void changeColorFromState(FieldState state, Button button) {
        if (null == state) {
            return;
        }
        switch (state) {
            case SEEN:
                addSeenMark(button);
                break;
            case DAMAGED:
                addDamagedMark(button);
                break;
            case DESTROYED:
                addDestroyedMark(button);
                break;
            default:
                break;
        }
    }

    private static void addSeenMark(Button button) {
        button.setText("O");
    }

    private static void addDamagedMark(Button button) {
        ObservableList<String> styles = button.getStyleClass();
        styles.removeAll(WATER_STYLE.toString());
        styles.add(DAMAGED_STYLE.toString());
    }

    private static void addDestroyedMark(Button button) {
        ObservableList<String> styles = button.getStyleClass();
        styles.removeAll(WATER_STYLE.toString());
        styles.add(DESTROYED_STYLE.toString());
    }
}
