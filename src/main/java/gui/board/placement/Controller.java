package gui.board.placement;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

/**
 * JavaFX standard application controller class
 */
public class Controller {

    private static final int BATTLESHIP = 4;
    private static final int CRUISER = 3;
    private static final int DESTROYER = 2;
    private static final int SUBMARINE = 1;

    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;

    @FXML
    private GridPane board;


    @FXML
    private void placeRandom() {
        int shipPosRow = 1;
        int shipPosCol = 5;

        int secondShipPosCol = 5;
        int secondShipPostRow = 5;
        createShip(BATTLESHIP, shipPosCol, shipPosRow, true);
        createShip(CRUISER, secondShipPosCol, secondShipPostRow, false);
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(FIELD_HEIGHT);
        rectangle.setWidth(FIELD_WIDTH);
        return rectangle;
    }


    private void createShip(int shipType, int columnPosition,
                            int rowPosition, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < shipType; i++) {
                board.add(createRectangle(), rowPosition, columnPosition);
                rowPosition++;
            }
        }
        else
        {
            for (int i = 0; i < shipType; i++) {
                board.add(createRectangle(), rowPosition, columnPosition);
                columnPosition++;
            }
        }
    }
}
