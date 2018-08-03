package gui.utility;

import connection.ServerConnection;
import gui.data.FieldBus;
import gui.data.FieldState;
import gui.printers.ShipPrinter;
import gui.receivers.ShotResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import static gui.styles.AvailableStyles.DAMAGED_STYLE;
import static gui.styles.AvailableStyles.DESTROYED_STYLE;
import static gui.styles.AvailableStyles.MISS_STYLE;
import static gui.styles.AvailableStyles.WATER_STYLE;

public class ShotBoardHandler {

    private static BlockingQueue<FieldBus> friendlyChangesBlockingQueue;
    private static List<FieldBus> list = new ArrayList<>();
    private static final int FIRST_INDEX = 0;

    private ShotBoardHandler() {
    }

    public static void friendlyShotReaction(ServerConnection serverConnection, GridPane gridPane) {
        friendlyChangesBlockingQueue = ShotResult.takeFriendlyBoardChanges(serverConnection);
        list.addAll(friendlyChangesBlockingQueue);
        ObservableList<FieldBus> observableList = FXCollections.observableList(list);
        if (!observableList.isEmpty()) {
            FieldBus fieldsToChange = observableList.get(FIRST_INDEX);
            changeBoardForFriendly(gridPane, fieldsToChange);
        }
    }

    private static void changeBoardForFriendly(GridPane friendlyBoard, FieldBus fieldsToChange) {
        Map<Coordinates, FieldState> fieldStateMap = fieldsToChange.getFieldStates();
        changeFriendlyFields(fieldStateMap, friendlyBoard);
    }

    private static void changeFriendlyFields(Map<Coordinates, FieldState> fieldStateMap, GridPane friendlyBoard) {
        fieldStateMap.forEach((key, value) -> {
            Rectangle mast = ShipPrinter.createMastRepresentation();
            changeColorFromState(value, mast);
            friendlyBoard.add(mast, key.getX(), key.getY());
        });
    }


    public static void enemyShotReaction(ServerConnection serverConnection, GridPane gridPane) {
        FieldBus fieldsToChange = ShotResult.takeEnemyBoardChanges(serverConnection);
        changeBoardForEnemy(gridPane, fieldsToChange);
    }

    private static void changeBoardForEnemy(GridPane gridPane, FieldBus fieldsToChange) {
        Map<Coordinates, FieldState> fieldStateMap = fieldsToChange.getFieldStates();
        takeEnemyFields(gridPane).forEach(field -> changeButtonsAsInFieldBus(fieldStateMap, field));
    }

    private static List<Button> takeEnemyFields(GridPane pane) {
        ObservableList<Node> nodes = pane.getChildren().filtered(x -> !x.getClass().equals(Group.class));
        List<Button> buttonList = new ArrayList<>();
        nodes.forEach(node -> buttonList.add((Button) node));
        return buttonList;
    }

    private static void changeButtonsAsInFieldBus(Map<Coordinates, FieldState> fieldStateMap, Button field) {
        Coordinates thisFieldCoordinates = createCoordinatesFromId(field);
        fieldStateMap.forEach((key, value) -> {
            if (key.equals(thisFieldCoordinates)) {
                changeColorFromState(value, field);
            }
        });
    }

    private static Coordinates createCoordinatesFromId(Button field) {
        String[] coordinateArray = field.getId().split(":");
        int x = Integer.parseInt(coordinateArray[0]);
        int y = Integer.parseInt(coordinateArray[1]);
        return Coordinates.create(x, y);
    }

    private static void changeColorFromState(FieldState state, Node node) {
        if (null == state) {
            return;
        }
        switch (state) {
            case SEEN:
                addSeenMark(node);
                break;
            case DAMAGED:
                addDamagedMark(node);
                break;
            case DESTROYED:
                addDestroyedMark(node);
                break;
            default:
                break;
        }
    }

    private static void addSeenMark(Node node) {
        ObservableList<String> styles = node.getStyleClass();
        styles.removeAll(WATER_STYLE.toString());
        styles.add(MISS_STYLE.toString());
    }

    private static void addDamagedMark(Node node) {
        ObservableList<String> styles = node.getStyleClass();
        styles.removeAll(WATER_STYLE.toString());
        styles.add(DAMAGED_STYLE.toString());
    }

    private static void addDestroyedMark(Node node) {
        ObservableList<String> styles = node.getStyleClass();
        styles.removeAll(WATER_STYLE.toString());
        styles.add(DESTROYED_STYLE.toString());
    }
}