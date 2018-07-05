package placement.controller;

import placement.model.Fleet;
import placement.model.Board;
import placement.model.Coordinates;
import placement.model.ship.Ship;
import placement.model.ship.Direction;

/**
 * Uses RandomFleetPlacer to randomize ship placement.
 */
class RandomFleetPlacer {

    private final Fleet fleet;
    private final Board board;

    RandomFleetPlacer(Fleet fleet, Board board) {
        this.fleet = fleet;
        this.board = board;
    }

    Fleet placeFleet() {
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
