package placement.controller;

import placement.model.*;

class RandomBoardCreator {

    private final Fleet fleet;
    private final Board board;

    RandomBoardCreator(Fleet fleet, Board board) {
        this.fleet = fleet;
        this.board = board;
    }

    Fleet generateFleet() {
        for (Ship ship : fleet.getShipList()) {
            while (!ship.isPlaced()) {
                Direction direction = Direction.getRandomDirection();
                ship.setDirection(direction);
                Coordinates randomCoordinates = Coordinates.getRandom(board.rows(), board.cols());
                board.placeShip(ship, randomCoordinates);
            }

        }
        return fleet;
    }
}
