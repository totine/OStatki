package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import placement.model.ship.DirectedShip;
import placement.model.ship.Ship;
import placement.model.ship.UndirectedShip;


import java.util.List;

public class FleetTests {
    @Test
    public void getShipListTest() {
        Fleet<UndirectedShip> defaultFleet = Fleet.createDefaultFleet();
        List<UndirectedShip> shipList = defaultFleet.getShipList();
        Assert.assertEquals(shipList.size(), 10);
    }
}
