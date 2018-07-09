package placement.model;

import placement.model.ship.DirectedShip;
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
 * Represents a default fleet of ships.
 */
public final class Fleet<T extends Ship> {
    private List<T> shipList;

    public  Fleet() {
        shipList = new ArrayList<>();
    }



    public static Fleet<UndirectedShip> createDefaultFleet() {
        Fleet<UndirectedShip> fleet = new Fleet<>();
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
