package placement.controller;

import placement.model.Board;
import placement.model.Fleet;

/**
 * contains methods related to fleet control.
 */
public class FleetController {

    public final Fleet generateFleet() {
        Fleet fleet = new Fleet();
        Board board = Board.createDefaultBoard();
        RandomBoardCreator randomBoardCreator = new RandomBoardCreator(fleet, board);
        return randomBoardCreator.generateFleet();
    }
}
