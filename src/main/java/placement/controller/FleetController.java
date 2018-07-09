package placement.controller;

import placement.model.board.Board;
import placement.model.Fleet;
import placement.model.ship.PlacedShip;

/**
 * contains methods related to fleet control.
 */
public final class FleetController {
    private FleetController() { }
    public static Fleet<PlacedShip> generatePlacedStandardFleet() {
        Fleet fleet = Fleet.createDefaultFleet();
        Board board = Board.createDefaultBoard();
        RandomFleetPlacer randomFleetPlacer = new RandomFleetPlacer(fleet, board);
        return randomFleetPlacer.placeFleet();
    }
}
