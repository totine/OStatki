package placement.controller;

import placement.model.Coordinates;
import placement.model.Fleet;
import placement.model.board.Board;
import placement.model.ship.Direction;
import placement.model.ship.DirectedShip;
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

    private RandomFleetPlacer(Fleet<UndirectedShip> fleet, Board board, Random random) {
        this.fleet = fleet;
        this.board = board;
        this.random = random;
    }

    static RandomFleetPlacer createPlacer(Fleet<UndirectedShip> fleet, Board board) {
        return new RandomFleetPlacer(fleet, board, new Random());
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
        Coordinates randomHeadCoordinates = getRandomCoordinates(board);
        DirectedShip directedShip = ship.direct(getRandomDirection());
        boolean isShipPlaced = ShipPlacer.tryToPlaceShip(board, directedShip, randomHeadCoordinates);
        if (isShipPlaced) {
            ship = PlacedShip.createFromDirectedShip(directedShip, randomHeadCoordinates);
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

    private Coordinates getRandomCoordinates(Board board) {
        int x = random.nextInt(board.rows());
        int y = random.nextInt(board.cols());
        return Coordinates.createCoordinates(x, y);
    }

}
