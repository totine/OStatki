package placement.controller;

import placement.model.Coordinates;
import placement.model.board.Board;
import placement.model.ship.DirectedShip;

import java.util.List;

/**
 * utility class with one method for placing one ship on the board
 */
public final class ShipPlacer {
    private ShipPlacer() {
    }

    public static boolean tryToPlaceShip(Board board, DirectedShip ship, Coordinates headCoordinates) {
        List<Coordinates> mastCoordinates = ship.getTempCoordinates(headCoordinates);
        if (mastCoordinates
                .stream()
                .allMatch(board::isMastPlaceable)) {
            mastCoordinates.forEach(board::placeMast);
            return true;
        }
        return false;
    }

}
