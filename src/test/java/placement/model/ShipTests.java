package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;
import placement.model.board.Board;
import placement.model.ship.Direction;
import placement.model.ship.DirectedShip;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShipTests {
//
//    @Test
//    public void givenNewlyCreatedShip_WhenCheckingItsStatus_ThenItIsNotPlaced() {
//        Board board = Board.createDefaultBoard();
//        DirectedShip ship = new DirectedShip(2, Direction.WEST);
//
//        Assert.assertFalse(ship.isPlaced());
//
//
//    }
//
//    @Test
//    public void givenNewlyCreatedShip_WhenWePlaceIt_ThenItsStatusIsPlaced() {
//        Board board = Board.createDefaultBoard();
//        DirectedShip ship = new DirectedShip(3, Direction.EAST);
//
//        board.placeShip(ship, 5, 4);
//
//        Assert.assertTrue(ship.isPlaced());
//    }
//
//    @Test
//    public void testGetCoordinates() {
//        Board board = Board.createDefaultBoard();
//
//        DirectedShip ship = new DirectedShip(4, Direction.NORTH);
//
//        board.placeShip(ship,7, 8);
//
//        List<Coordinates> list = Arrays.asList(
//                new Coordinates(7, 8),
//                new Coordinates(7, 7),
//                new Coordinates(7, 6),
//                new Coordinates(7, 5));
//
//        Set<Coordinates> expectedCoordinates = new HashSet<>(list);
//
//        Set<Coordinates> actualCoordinates = new HashSet<>(ship.getPositionCoordinates());
//
//        Assert.assertEquals(expectedCoordinates, actualCoordinates);
//    }
}
