package placement.model;

import placement.model.ship.Ship;
import placement.model.ship.ShipType;

import java.util.ArrayList;
import java.util.List;

import static placement.model.ship.ShipType.*;

/**
 * Represents a default fleet of ships.
 */
public class Fleet {
    private List<Ship> shipList;


    private Fleet(ShipType... shipTypes) {
        shipList = new ArrayList<>();
        for (ShipType shipType : shipTypes) {
            Ship ship = new Ship(shipType.getSize());
            shipList.add(ship);
        }
    }

    public static Fleet createDefaultFleet() {
        return new Fleet(BATTLESHIP,
                CRUISER, CRUISER,
                DESTROYER, DESTROYER, DESTROYER,
                SUBMARINE, SUBMARINE, SUBMARINE, SUBMARINE);
    }

    public final List<Ship> getShipList() {
        return shipList;
    }
}
