package model.placement;

import model.Coordinates;
import org.testng.Assert;
import org.testng.annotations.Test;

import game.placement.ShipPlacer;
import model.placement.board.Board;
import model.placement.ship.*;

import java.util.*;


public class ShipTests {

    @Test
    public void givenNewlyCreatedShip_WhenCheckingItsStatus_ThenItIsNotPlaced() {

        DirectedShip ship = new DirectedShip(2, Direction.WEST);

        Assert.assertFalse(ship.isPlaced());


    }

    @Test
    public void givenNewlyCreatedShip_WhenWePlaceIt_ThenItsStatusIsPlaced() {
        Board board = Board.createDefaultBoard();
        DirectedShip ship = new DirectedShip(3, Direction.EAST);

        boolean isPlaced = ShipPlacer.tryToPlaceShip(board, ship, Coordinates.create(5, 4));

        Assert.assertTrue(isPlaced);

    }

    @Test
    public void testGetCoordinates() {
        Board board = Board.createDefaultBoard();


        DirectedShip ship = new DirectedShip(4, Direction.NORTH);

        boolean isPlaced = ShipPlacer.tryToPlaceShip(board, ship, Coordinates.create(7, 8));


        Assert.assertTrue(isPlaced);

    }

    @Test
    public void undirectedShip_isNotPlaced() {
        UndirectedShip ship = new UndirectedShip(4);
        Assert.assertFalse(ship.isPlaced());
    }

    @Test
    public void undirectedShip_afterDirect_returnsDirectedShip() {
        UndirectedShip ship = new UndirectedShip(4);

        Ship directShip = ship.direct(Direction.WEST);

        Assert.assertEquals(directShip.getClass(), DirectedShip.class);
    }

    @Test
    public void directedShip_afterDirect_returnsDirectedShip() {
        DirectedShip ship = new DirectedShip(2, Direction.SOUTH);

        Ship directShip = ship.direct(Direction.NORTH);

        Assert.assertEquals(directShip.getClass(), DirectedShip.class);
    }

    @Test
    public void placedShip_afterDirect_returnsDirectedShip() {
        PlacedShip ship = new PlacedShip(new ArrayList<>());

        Ship directShip = ship.direct(Direction.NORTH);

        Assert.assertEquals(directShip.getClass(), DirectedShip.class);
    }
}
