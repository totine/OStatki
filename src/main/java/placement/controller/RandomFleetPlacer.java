package placement.controller;

import placement.model.Fleet;
import placement.model.board.Board;
import placement.model.Coordinates;
import placement.model.ship.DirectedShip;
import placement.model.ship.Direction;
import placement.model.ship.PlacedShip;
import placement.model.ship.Ship;
import placement.model.ship.UndirectedShip;


import java.util.Random;

/**
 * Uses RandomFleetPlacer to randomize ship placement.
 */
class RandomFleetPlacer {

    private final Fleet<UndirectedShip> fleet;
    private final Board board;
    private final Random random;


    RandomFleetPlacer(Fleet<UndirectedShip> fleet, Board board) {
        this.fleet = fleet;
        this.board = board;
        this.random = new Random();
    }


    Fleet<PlacedShip> placeFleet() {
        Fleet<PlacedShip> fleetToSend = new Fleet<>();
        for (Ship ship : fleet.getShipList()) {
            PlacedShip placedShip = (PlacedShip) getShipToPlace(ship);
            fleetToSend.add(placedShip);
        }
        return fleetToSend;
    }

    private Ship getShipToPlace(Ship ship) {
        while (!ship.isPlaced()) {
            ship = getShipAfterTryToPlace(ship);
        }
        return ship;
    }

    private Ship getShipAfterTryToPlace(Ship ship) {
        Direction direction = getRandomDirection();
        Coordinates randomHeadCoordinates = getRandomCoordinates(board.rows(), board.cols());
        DirectedShip directedShip = ship.direct(direction);
        boolean isShipPlaced = ShipPlacer.tryToPlaceShip(board, directedShip, randomHeadCoordinates);
        if (isShipPlaced) {
            ship = new PlacedShip(directedShip, randomHeadCoordinates);
        } else {
            ship = directedShip;
        }
        return ship;
    }

    private Direction getRandomDirection() {
        Direction[] directions = Direction.class.getEnumConstants();
        int index = random.nextInt(directions.length);
        return directions[index];
    }

    private Coordinates getRandomCoordinates(int rows, int cols) {
        int x = random.nextInt(rows);
        int y = random.nextInt(cols);
        return new Coordinates(x, y);
    }

}
