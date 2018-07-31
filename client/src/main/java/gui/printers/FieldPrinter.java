package gui.printers;

import connection.ServerConnection;
import gui.instance.ClientAppRunner;
import gui.utility.Command;
import gui.utility.JSONConverter;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Coordinates;

import static gui.styles.AvailableStyles.WATER_STYLE;
import static gui.utility.CommandType.SHOT;

/**
 * This class is made to help with printing fields as buttons on enemy board.
 */
public class FieldPrinter {
    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;
    private static ServerConnection serverConnection = ClientAppRunner.getInstance().getServerConnection();

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
        shotAndWaitOnAction(field, widthCoordinates, heightCoordinates);
    }

    private static void setDimensions(Button field) {
        field.setPrefHeight(FIELD_HEIGHT);
        field.setPrefWidth(FIELD_WIDTH);
    }

    private static void addStyle(Button field) {
        field.getStyleClass().add(WATER_STYLE.toString());
    }

    private static void shotAndWaitOnAction(Button field, int x, int y) {
        Command shotCommand = Command.withType(SHOT, Coordinates.create(x, y));
        String coordinatesToPass = JSONConverter.convertToJSON(shotCommand);

        field.setId(x + ":" + y);

        field.setOnAction(event -> {
            serverConnection.sendMessage(coordinatesToPass);
            //todo add update board after sending shot
        });
    }


}
