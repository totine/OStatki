package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import placement.model.ship.Ship;

import java.util.List;

public class FleetTests {
    @Test
    public void getShipListTest() {
        Fleet defaultFleet = Fleet.createDefaultFleet();
        List<Ship> shipList = defaultFleet.getShipList();

        Assert.assertEquals(shipList.size(), 10);
    }
}
