package placement.model;

import placement.model.ship.Ship;
import placement.model.ship.ShipType;
import placement.model.ship.UndirectedShip;


import java.util.ArrayList;
import java.util.List;

import static placement.model.ship.ShipType.SHIP_MAST1;
import static placement.model.ship.ShipType.SHIP_MAST2;
import static placement.model.ship.ShipType.SHIP_MAST3;
import static placement.model.ship.ShipType.SHIP_MAST4;

/**
 * Represents a fleet of ships.
 * @param <T> fleet can be generic - it is able to store different types of Ships.
 */
public final class Fleet<T extends Ship> {
    private List<T> shipList;

    public Fleet() {
        shipList = new ArrayList<>();
    }

    public static Fleet<UndirectedShip> createDefaultFleet() {
        Fleet<UndirectedShip> fleet = new Fleet<>();
        ShipType[] shipTypes = {SHIP_MAST4,
                SHIP_MAST3, SHIP_MAST3,
                SHIP_MAST2, SHIP_MAST2, SHIP_MAST2,
                SHIP_MAST1, SHIP_MAST1, SHIP_MAST1, SHIP_MAST1};
        for (ShipType shipType : shipTypes) {
            UndirectedShip ship = new UndirectedShip(shipType.getSize());
            fleet.add(ship);
        }
        return fleet;
    }

    public List<T> getShipList() {
        return shipList;
    }

    public void add(T ship) {
        shipList.add(ship);
    }
}
