package gui.printers;

import connection.ServerConnection;
import gui.instance.ClientAppRunner;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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

    private static Button createField() {
        Button field = new Button();
        prepareField(field);
        return field;
    }

    private static void prepareField(Button field) {
        setDimensions(field);
        addStyle(field);
        addMissOnClick(field);
    }

    private static void addStyle(Button field) {
        field.getStyleClass().add("water-button");
    }

    private static void addMissOnClick(Button field) {
        ClientAppRunner appInstance = ClientAppRunner.getInstance();
        ServerConnection serverConnection = appInstance.getServerConnection();
        field.setOnAction(event -> serverConnection.sendMessage(SHOT_COMMAND));
    }

    private static void setDimensions(Button field) {
        field.setPrefHeight(FIELD_HEIGHT);
        field.setPrefWidth(FIELD_WIDTH);
    }


    private static void addToEachSpace(int numberOfRows, int numberOfColumns, GridPane printingBoard) {
        for (int widthPosition = 0; widthPosition < numberOfRows; widthPosition++) {
            for (int heightPosition = 0; heightPosition < numberOfColumns; heightPosition++) {
                Button fieldToClick = createField();
                printingBoard.add(fieldToClick, widthPosition, heightPosition);
            }
        }
    }
}
