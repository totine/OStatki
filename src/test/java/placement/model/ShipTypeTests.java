package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;
import placement.model.ship.ShipType;

public class ShipTypeTests {
    @Test
    public void testSubmarineSize() {
        Assert.assertEquals(ShipType.SUBMARINE.getSize(), 1);
    }

    @Test
    public void testDestroyerSize() {
        Assert.assertEquals(ShipType.DESTROYER.getSize(), 2);
    }

    @Test
    public void testCruiserSize() {
        Assert.assertEquals(ShipType.CRUISER.getSize(), 3);
    }

    @Test
    public void testBattleshipSize() {
        Assert.assertEquals(ShipType.BATTLESHIP.getSize(), 4);
    }
}
