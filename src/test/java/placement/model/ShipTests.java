package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import placement.controller.ShipPlacer;
import placement.model.board.Board;
import placement.model.ship.*;

import java.util.*;




public class ShipTests {

    @Test
    public void givenNewlyCreatedShip_WhenCheckingItsStatus_ThenItIsNotPlaced() {
        Board board = Board.createDefaultBoard();

        DirectedShip ship = new DirectedShip(2, Direction.WEST);

        Assert.assertFalse(ship.isPlaced());


    }

    @Test
    public void givenNewlyCreatedShip_WhenWePlaceIt_ThenItsStatusIsPlaced() {
        Board board = Board.createDefaultBoard();
        DirectedShip ship = new DirectedShip(3, Direction.EAST);

        Ship placedShip = ShipPlacer.placeShip(board, ship, 5, 4);

        Assert.assertTrue(placedShip.isPlaced());

    }

    @Test
    public void testGetCoordinates() {
        Board board = Board.createDefaultBoard();


        DirectedShip ship = new DirectedShip(4, Direction.NORTH);

        Ship ship1 = ShipPlacer.placeShip(board, ship, 7, 8);

        List<Coordinates> expectedCoordinates = Arrays.asList(
                new Coordinates(7, 8),
                new Coordinates(7, 7),
                new Coordinates(7, 6),
                new Coordinates(7, 5));

        List<Coordinates> actualCoordinates = ((PlacedShip) ship1).getMastCoordinates();

        Assert.assertTrue(ship1.isPlaced());
        Assert.assertEquals(actualCoordinates, expectedCoordinates);
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
