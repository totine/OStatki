package placement.controller;

import placement.model.Fleet;
import placement.model.board.Board;
import placement.model.Coordinates;
import placement.model.ship.Ship;
import placement.model.ship.Direction;

import java.util.Random;

/**
 * Uses RandomFleetPlacer to randomize ship placement.
 */
class RandomFleetPlacer {

    private final Fleet fleet;
    private final Board board;
    private final Random random;


    RandomFleetPlacer(Fleet fleet, Board board) {
        this.fleet = fleet;
        this.board = board;
        this.random = new Random();
    }



    Fleet placeFleet() {
        for (Ship ship : fleet.getShipList()) {
            while (!ship.isPlaced()) {
                Direction direction = getRandomDirection();
                ship.setDirection(direction);
                Coordinates randomCoordinates = getRandomCoordinates(board.rows(), board.cols());
                board.placeShip(ship, randomCoordinates);
            }
        }
        return fleet;
    }

    public Direction getRandomDirection() {
        Direction[] directions = Direction.class.getEnumConstants();
        int index = random.nextInt(directions.length - 1);
        return directions[index];
    }

    public Coordinates getRandomCoordinates(int rows, int cols) {
        int x = random.nextInt(rows);
        int y = random.nextInt(cols);
        return new Coordinates(x, y);
    }

}
