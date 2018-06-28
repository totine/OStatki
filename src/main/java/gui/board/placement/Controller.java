package gui.board.placement;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Controller {

    private static final int BATTLESHIP = 4;
    private static final int CRUISER = 3;
    private static final int DESTROYER = 2;
    private static final int SUBMARINE = 1;

    @FXML
    private GridPane board;

    @FXML
    private void placeRandom() {
        createShip(BATTLESHIP,1,2,true);
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(50);
        rectangle.setWidth(50);
        return rectangle;
    }

    private void createShip(int shipType, int columnPosition, int rowPosition, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < shipType; i++) {
                board.add(createRectangle(),rowPosition,columnPosition);
                rowPosition++;
            }
        }
    }
}
