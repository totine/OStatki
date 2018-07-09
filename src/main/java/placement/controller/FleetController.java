package placement.controller;

import placement.model.board.Board;
import placement.model.Fleet;
import placement.model.ship.PlacedShip;
import placement.model.ship.Ship;


/**
 * contains methods related to fleet control.
 */
public final class FleetController {
    private FleetController() { }

    public static Fleet<PlacedShip> generatePlacedStandardFleet() {
        Fleet<Ship> fleet = Fleet.createDefaultFleet();
        Board board = Board.createDefaultBoard();
        RandomFleetPlacer randomFleetPlacer = new RandomFleetPlacer(fleet, board);
        return randomFleetPlacer.placeFleet();
    }
}
