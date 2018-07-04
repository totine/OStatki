package placement.view;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import placement.controller.FleetController;
import placement.model.Fleet;
import placement.model.Ship;
import placement.model.Coordinates;

/**
 * JavaFX standard application controller class
 */
public class GUIPlacementController {

    private FleetController fleetController = new FleetController();

    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;

    @FXML
    private GridPane guiBoard;


    @FXML
    private void placeRandom() {
        guiBoard.getChildren().removeIf(node -> node instanceof Shape);
        Fleet fleet = fleetController.generatePlacedStandardFleet();
        for (Ship ship : fleet.getShipList()) {
            printShip(ship);
        }
    }

    private Rectangle createMast() {
        Rectangle mast = new Rectangle();
        mast.setHeight(FIELD_HEIGHT);
        mast.setWidth(FIELD_WIDTH);

        return mast;
    }


    private void printShip(Ship ship) {

        for (Coordinates coord : ship.getDirectionCoordinates()) {
            Shape next = createMast();
            guiBoard.add(next, coord.getX(), coord.getY());
        }


    }
}
