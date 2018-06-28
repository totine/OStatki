package gui.board.placement;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Controller {

    @FXML
    private GridPane board;

    @FXML
    private void placeRandom() {
        for (int i = 0; i < 10; i++) {
            board.add(createRectangle(),i,i);
        }
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(50);
        rectangle.setWidth(50);
        return rectangle;
    }
}
