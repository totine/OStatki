package placement.controller;

import placement.model.board.Board;
import placement.model.Fleet;
import placement.model.ship.PlacedShip;
import placement.model.ship.UndirectedShip;


/**
 * contains methods related to fleet control.
 */
public final class FleetController {
    private FleetController() { }

    public static Fleet<PlacedShip> generatePlacedStandardFleet() {
        Fleet<UndirectedShip> fleet = Fleet.createDefaultFleet();
        Board board = Board.createDefaultBoard();
        RandomFleetPlacer randomFleetPlacer = RandomFleetPlacer.createPlacer(fleet, board);
        return randomFleetPlacer.placeFleet();
    }
}
