package gui.printers;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Coordinates;

/**
 * This class is made to help with printing ship representation on friendly board.
 */
public class ShipPrinter {

    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;

    public static void printFleet(FleetView fleet, GridPane printingBoard) {
        if (checkIfFleetExists(fleet)) {
            for (ShipView ship : fleet.getShipList()) {
                printShip(ship, printingBoard);
            }
        }
    }

    private static boolean checkIfFleetExists(FleetView fleet) {
        return null != fleet;
    }

    private static void printShip(ShipView ship, GridPane printingBoard) {
        for (Coordinates coordinates : ship.getPositionCoordinates()) {
            Shape next = createMastRepresentation();
            printingBoard.add(next, coordinates.getColumnIndex(), coordinates.getRowIndex());
        }
    }

    private static Rectangle createMastRepresentation() {
        Rectangle mast = new Rectangle();
        mast.setHeight(FIELD_HEIGHT);
        mast.setWidth(FIELD_WIDTH);

        return mast;
    }

    private ShipPrinter() {
    }
}
