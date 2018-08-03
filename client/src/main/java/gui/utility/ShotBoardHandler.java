package gui.utility;

import connection.ServerConnection;
import gui.data.FieldBus;
import gui.data.FieldState;
import gui.printers.ShipPrinter;
import gui.receivers.ShotResult;
import gui.styles.AvailableStyles;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static gui.styles.AvailableStyles.DAMAGED_STYLE;
import static gui.styles.AvailableStyles.DESTROYED_STYLE;
import static gui.styles.AvailableStyles.SEEN_STYLE;
import static gui.styles.AvailableStyles.WATER_STYLE;

public class ShotBoardHandler {
    private static final String SEEN_COLOR = "#0004dc";
    private static final String DAMAGED_COLOR = "#960000";
    private static final String DESTROYED_COLOR = "#850000";
    public static final String BLACK_COLOR = "#000000";

    private ShotBoardHandler() {
    }

    public static void friendlyShotReaction(ServerConnection serverConnection, GridPane gridPane) {
        FieldBus fieldsToChange = ShotResult.takeFriendlyBoardChanges(serverConnection);
        changeBoardForFriendly(gridPane, fieldsToChange);
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

    private static void changeColorFromState(FieldState state, Node node) {
        if (null == state) {
            return;
        }
        switch (state) {
            case SEEN:
                addStyleOrColor(node, SEEN_STYLE, SEEN_COLOR);
                break;
            case DAMAGED:
                addStyleOrColor(node, DAMAGED_STYLE, DAMAGED_COLOR);
                break;
            case DESTROYED:
                addStyleOrColor(node, DESTROYED_STYLE, DESTROYED_COLOR);
                break;
            default:
                break;
        }
    }

    private static void addStyleOrColor(Node node, AvailableStyles styles, String color) {
        ObservableList<String> currentStyles = node.getStyleClass();
        if (checkIfButton(node)) {
            currentStyles.removeAll(WATER_STYLE.toString());
            currentStyles.add(styles.toString());
        } else if (checkIfRectangle(node)) {
            setColorForRectangle(node, color);
        }
    }

    private static boolean checkIfButton(Node node) {
        return node.getClass().equals(Button.class);
    }

    private static boolean checkIfRectangle(Node node) {
        return node.getClass().equals(Rectangle.class);
    }

    private static void setColorForRectangle(Node node, String color) {
        Rectangle rectangle = (Rectangle) node;
        rectangle.setFill(Paint.valueOf(color));
        rectangle.setStroke(Paint.valueOf(BLACK_COLOR));
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
}
