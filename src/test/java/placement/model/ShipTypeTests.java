package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;
import placement.model.ship.ShipType;

public class ShipTypeTests {
    @Test
    public void testSubmarineSize() {
        Assert.assertEquals(ShipType.SHIP_MAST1.getSize(), 1);
    }

    @Test
    public void testDestroyerSize() {
        Assert.assertEquals(ShipType.SHIP_MAST2.getSize(), 2);
    }

    @Test
    public void testCruiserSize() {
        Assert.assertEquals(ShipType.SHIP_MAST3.getSize(), 3);
    }

    @Test
    public void testBattleshipSize() {
        Assert.assertEquals(ShipType.SHIP_MAST4.getSize(), 4);
    }
}
