package gui.printers;

import connection.ServerConnection;
import gui.data.FieldBus;
import gui.data.FieldState;
import gui.instance.ClientAppRunner;
import gui.receivers.ShotResult;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Coordinates;
import static gui.styles.AvailableStyles.DAMAGED_STYLE;
import static gui.styles.AvailableStyles.DESTROYED_STYLE;
import static gui.styles.AvailableStyles.WATER_STYLE;
/**
 * This class is made to help with printing fields as buttons on enemy board.
 */
public class FieldPrinter {
    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;

    private static final String SHOT_COMMAND = "SHOT";

    private FieldPrinter() {
    }

    public static void insertFields(GridPane printingBoard) {
        int numberOfRows = printingBoard.getRowConstraints().size();
        int numberOfColumns = printingBoard.getColumnConstraints().size();
        addToEachSpace(numberOfRows, numberOfColumns, printingBoard);
    }

    private static void addToEachSpace(int numberOfRows, int numberOfColumns, GridPane printingBoard) {
        for (int widthPosition = 0; widthPosition < numberOfRows; widthPosition++) {
            for (int heightPosition = 0; heightPosition < numberOfColumns; heightPosition++) {
                Button fieldToClick = createField(widthPosition, heightPosition);
                printingBoard.add(fieldToClick, widthPosition, heightPosition);
            }
        }
    }

    private static Button createField(int widthCoordinates, int heightCoordinates) {
        Button field = new Button();
        prepareField(field, widthCoordinates, heightCoordinates);
        return field;
    }

    private static void prepareField(Button field, int widthCoordinates, int heightCoordinates) {
        setDimensions(field);
        addStyle(field);
        addMockOnAction(field, widthCoordinates, heightCoordinates);
    }

    private static void setDimensions(Button field) {
        field.setPrefHeight(FIELD_HEIGHT);
        field.setPrefWidth(FIELD_WIDTH);
    }

    private static void addStyle(Button field) {
        field.getStyleClass().add(WATER_STYLE.toString());
    }

    private static void addShotOnAction(Button field, int widthCoordinates, int heightCoordinates) {
        ClientAppRunner appInstance = ClientAppRunner.getInstance();
        ServerConnection serverConnection = appInstance.getServerConnection();

        String coordinatesToPass = widthCoordinates + "-" + heightCoordinates;
        field.setOnAction(event -> serverConnection.sendMessage(SHOT_COMMAND + ":" + coordinatesToPass));
    }

    private static void addMockOnAction(Button field, int widthCoordinates, int heightCoordinates) {
        FieldBus fieldBusToChange = ShotResult.getShotInformation(ShotResult.outputSupplier());
        Coordinates coordinatesOfField = Coordinates.create(widthCoordinates, heightCoordinates);

        FieldState stateOfField = fieldBusToChange.getCoordinateState(coordinatesOfField);
        changeColorFromState(stateOfField, field);
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
