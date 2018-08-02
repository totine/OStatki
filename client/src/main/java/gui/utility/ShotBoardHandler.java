package gui.utility;

import connection.ServerConnection;
import gui.data.FieldBus;
import gui.data.FieldState;
import gui.printers.ShipView;
import gui.receivers.ShotResult;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static gui.styles.AvailableStyles.DAMAGED_STYLE;
import static gui.styles.AvailableStyles.DESTROYED_STYLE;
import static gui.styles.AvailableStyles.WATER_STYLE;

public class ShotBoardHandler {

    private static final int FIELD_HEIGHT = 30;
    private static final int FIELD_WIDTH = 30;

    private ShotBoardHandler() {
    }

    public static void takeReactionFromServer(ServerConnection serverConnection, GridPane opponentBoard, GridPane myBoard) throws InterruptedException {
        FieldBus fieldBusToChangeOpponentBoard =serverConnection.getOpponentBoardChanges();
        Map<Coordinates, FieldState> fieldStateMapOpponentBoard = fieldBusToChangeOpponentBoard.getFieldStates();
        takeGridPaneFields(opponentBoard).forEach(field -> changeFieldsAsInFieldBus(fieldStateMapOpponentBoard, field));
        FieldBus fieldBusToChangeMyBoard = serverConnection.getMyBoardChanges();
        Map<Coordinates, FieldState> fieldStateMapMyBoard = fieldBusToChangeMyBoard.getFieldStates();
        fieldStateMapMyBoard.forEach((coord, state) -> printShip(coord, state, myBoard));
    }

    private static void printShip(Coordinates coordinates, FieldState state, GridPane printingBoard) {
            Shape next = createMastRepresentation(state);
            printingBoard.add(next, coordinates.getColumnIndex(), coordinates.getRowIndex());
        }


    private static Rectangle createMastRepresentation(FieldState state) {
        Rectangle mast = new Rectangle();
        mast.setHeight(FIELD_HEIGHT);
        mast.setWidth(FIELD_WIDTH);
        changeColorFromState(state, mast);
        return mast;
    }

    private static void changeColorFromState(FieldState state, Shape button) {
        if (null == state) {
            return;
        }
        switch (state) {
            case SEEN:
                addSeenMarkRect(button);
                break;
            case DAMAGED:
                addDamagedMarkRect(button);
                break;
            case DESTROYED:
                addDestroyedMarkRect(button);
                break;
            default:
                break;
        }
    }

    private static void addSeenMarkRect(Shape button) {
        button.setFill(Paint.valueOf("GREY"));
    }

    private static void addDamagedMarkRect(Shape button) {
        ObservableList<String> styles = button.getStyleClass();
        styles.removeAll(WATER_STYLE.toString());
        styles.add(DAMAGED_STYLE.toString());
    }

    private static void addDestroyedMarkRect(Shape button) {
        ObservableList<String> styles = button.getStyleClass();
        styles.removeAll(WATER_STYLE.toString());
        styles.add(DESTROYED_STYLE.toString());
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
