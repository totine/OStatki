package model.placement;

import model.placement.fleet.Fleet;
import org.testng.Assert;
import org.testng.annotations.Test;

import model.placement.ship.UndirectedShip;


import java.util.List;

public class FleetTests {
    @Test
    public void getShipListTest() {
        Fleet<UndirectedShip> defaultFleet = Fleet.createDefaultFleet();
        List<UndirectedShip> shipList = defaultFleet.getShipList();
        Assert.assertEquals(shipList.size(), 10);
    }
}
