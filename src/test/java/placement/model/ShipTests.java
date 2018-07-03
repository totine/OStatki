package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ShipTests {

    @Test
    public void givenNewlyCreatedShip_WhenCheckingItsStatus_ThenItIsNotPlaced() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(2, Direction.WEST);

        Assert.assertFalse(ship.isPlaced());

        board.placeShip(ship, 5, 4);

        Assert.assertTrue(ship.isPlaced());
    }
}
