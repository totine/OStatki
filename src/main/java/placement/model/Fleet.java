package placement.model;

import placement.model.ship.Ship;
import placement.model.ship.ShipType;
import placement.model.ship.UndirectedShip;

import java.util.ArrayList;
import java.util.List;

import static placement.model.ship.ShipType.SUBMARINE;
import static placement.model.ship.ShipType.DESTROYER;
import static placement.model.ship.ShipType.CRUISER;
import static placement.model.ship.ShipType.BATTLESHIP;

/**
 * Represents a fleet of ships.
 * @param <T> fleet can be generic - it is able to store different types of Ships.
 */
public final class Fleet<T extends Ship> {
    private List<T> shipList;

    public Fleet() {
        shipList = new ArrayList<>();
    }

    public static Fleet<Ship> createDefaultFleet() {
        Fleet<Ship> fleet = new Fleet<>();
        ShipType[] shipTypes = {BATTLESHIP,
                CRUISER, CRUISER,
                DESTROYER, DESTROYER, DESTROYER,
                SUBMARINE, SUBMARINE, SUBMARINE, SUBMARINE};
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
