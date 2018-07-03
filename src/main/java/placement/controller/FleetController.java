package placement.controller;

import placement.model.Board;
import placement.model.Fleet;

public class FleetController {

    public Fleet generateFleet() {
        Fleet fleet = new Fleet();
        Board board = new Board();
        RandomBoardCreator randomBoardCreator = new RandomBoardCreator(fleet, board);
        return randomBoardCreator.generateFleet();
    }
}
