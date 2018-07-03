package placement.view;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import placement.controller.FleetController;
import placement.model.*;

/**
 * JavaFX standard application controller class
 */
public class PlacementController {

    private FleetController fleetController = new FleetController();

    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;

    @FXML
    private GridPane board;


    @FXML
    private void placeRandom() {
        board.getChildren().removeIf(node -> node instanceof Shape);
        Fleet fleet = fleetController.generateFleet();
        System.out.println(board);
        for (Ship ship : fleet.getShipList()) {
            createShip(ship);
        }
    }

    private Rectangle createMast() {
        Rectangle mast = new Rectangle();
        mast.setHeight(FIELD_HEIGHT);
        mast.setWidth(FIELD_WIDTH);
        return mast;
    }


    private void createShip(Ship ship) {
        for (Coordinates coord : ship.getDirectionCoordinates()) {
            Shape next = createMast();
            board.add(next, coord.getX(), coord.getY());
        }


    }
}
