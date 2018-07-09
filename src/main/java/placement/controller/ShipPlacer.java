package placement.controller;

import placement.model.Coordinates;
import placement.model.board.Board;
import placement.model.ship.DirectedShip;
import placement.model.ship.PlacedShip;
import placement.model.ship.Ship;
import placement.model.ship.UndirectedShip;

import java.util.List;

public class ShipPlacer {
    public static Ship placeShip(Board board, DirectedShip ship, Coordinates headCoordinates) {
        List<Coordinates> mastCoordinates = ship.getTempCoordinates(headCoordinates);
        if(mastCoordinates.stream().allMatch(board::isMastPlaceable)) {
            mastCoordinates.forEach(board::placeMast);
            return new PlacedShip(mastCoordinates);
        }
        return new UndirectedShip(ship.getMastNumber());
    }

    public static Ship placeShip(Board board, DirectedShip ship1, int x, int y) {
        return placeShip(board, ship1, new Coordinates(x, y));
    }


}
