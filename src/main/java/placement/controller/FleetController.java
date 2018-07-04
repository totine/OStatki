package placement.controller;

import placement.model.Board;
import placement.model.Fleet;

/**
 * contains methods related to fleet control.
 */
public class FleetController {

    public static Fleet generatePlacedStandardFleet() {
        Fleet fleet = Fleet.createDefaultFleet();
        Board board = Board.createDefaultBoard();
        RandomFleetPlacer randomFleetPlacer = new RandomFleetPlacer(fleet, board);
        return randomFleetPlacer.generateFleet();
    }
}
