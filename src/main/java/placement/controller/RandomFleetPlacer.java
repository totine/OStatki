package placement.controller;

import placement.model.Fleet;
import placement.model.board.Board;
import placement.model.Coordinates;
import placement.model.ship.DirectedShip;
import placement.model.ship.Direction;
import placement.model.ship.PlacedShip;
import placement.model.ship.Ship;

import java.util.Random;

/**
 * Uses RandomFleetPlacer to randomize ship placement.
 */
class RandomFleetPlacer {

    private final Fleet<Ship> fleet;
    private final Board board;
    private final Random random;


    RandomFleetPlacer(Fleet<Ship> fleet, Board board) {
        this.fleet = fleet;
        this.board = board;
        this.random = new Random();
    }

    Fleet<PlacedShip> placeFleet() {
        Fleet<PlacedShip> fleetToSend = new Fleet<>();
        for (Ship ship : fleet.getShipList()) {
            while (!ship.isPlaced()) {
                Direction direction = getRandomDirection();
                DirectedShip ship2 = ship.direct(direction);
                Coordinates randomCoordinates = getRandomCoordinates(board.rows(), board.cols());
                ship = ShipPlacer.placeShip(board, ship2, randomCoordinates);
            }
            fleetToSend.add((PlacedShip) ship);
        }
        return fleetToSend;
    }

    private Direction getRandomDirection() {
        Direction[] directions = Direction.class.getEnumConstants();
        int index = random.nextInt(directions.length - 1);
        return directions[index];
    }

    private Coordinates getRandomCoordinates(int rows, int cols) {
        int x = random.nextInt(rows);
        int y = random.nextInt(cols);
        return new Coordinates(x, y);
    }

}
