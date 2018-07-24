package controller;


import model.Coordinates;
import model.placement.board.Board;
import model.placement.ship.DirectedShip;

import java.util.List;

/**
 * utility class with one method for placing one ship on the board
 */
public final class ShipPlacer {
    private ShipPlacer() {
    }

    public static boolean tryToPlaceShip(Board board, DirectedShip ship, Coordinates headCoordinates) {
        List<Coordinates> mastCoordinates = ship.createTempCoordinatesList(headCoordinates);
        if (canBePlaced(board, mastCoordinates)) {
            mastCoordinates.forEach(board::placeMast);
            return true;
        }
        return false;
    }

    private static boolean canBePlaced(Board board, List<Coordinates> mastCoordinates) {
        return mastCoordinates
                .stream()
                .allMatch(board::mastCanBePlaced);
    }

}
