package gui.board.placement;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import placement.model.*;

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
        board.getChildren().clear();
        Fleet fleet = new Fleet();
        Board board = new Board();
        RandomBoardCreator randomBoardCreator = new RandomBoardCreator(fleet, board);
        Fleet fleetToSend = randomBoardCreator.generateFleet();
        System.out.println(board);
        for (Ship ship : fleetToSend.getShipList()) {
            createShip(ship);
        }
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(FIELD_HEIGHT);
        rectangle.setWidth(FIELD_WIDTH);
        return rectangle;
    }


    private void createShip(Ship ship) {
        Shape start = createRectangle();

        board.add(start, ship.getHeadCoordinates().getX(), ship.getHeadCoordinates().getY());
        for (Coordinates coord : ship.getDirectionCoordinates()) {

            Shape next = createRectangle();

            board.add(next, coord.getX(), coord.getY());

            }



    }
}
