package controller;


import model.placement.board.Board;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;
import model.placement.ship.UndirectedShip;


/**
 * contains methods related to fleet control.
 */
public final class FleetController {
    private FleetController() {
    }

    public static Fleet<PlacedShip> generatePlacedStandardFleet() {
        Fleet<UndirectedShip> fleet = Fleet.createDefaultFleet();
        Board board = Board.createDefaultBoard();
        RandomFleetPlacer randomFleetPlacer = RandomFleetPlacer.createPlacer(fleet, board);
        return randomFleetPlacer.placeFleet();
    }
}
