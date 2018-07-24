package model.placement;

import org.testng.Assert;
import org.testng.annotations.Test;
import model.placement.ship.ShipType;

public class ShipTypeTests {
    @Test
    public void testSubmarineSize() {
        Assert.assertEquals(ShipType.ONE_MAST.getSize(), 1);
    }

    @Test
    public void testDestroyerSize() {
        Assert.assertEquals(ShipType.TWO_MAST.getSize(), 2);
    }

    @Test
    public void testCruiserSize() {
        Assert.assertEquals(ShipType.THREE_MAST.getSize(), 3);
    }

    @Test
    public void testBattleshipSize() {
        Assert.assertEquals(ShipType.FOUR_MAST.getSize(), 4);
    }
}
