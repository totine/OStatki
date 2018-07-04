package placement.model;

import placement.model.ship.Ship;

import java.util.ArrayList;
import java.util.List;

import static placement.model.ship.ShipTypes.*;

/**
 * Represents a default fleet of ships.
 */
public class Fleet {
    private List<Ship> shipList;


    private Fleet() {
        this(BATTLESHIP.getSize(),
                CRUISER.getSize(), CRUISER.getSize(),
                DESTROYER.getSize(), DESTROYER.getSize(), DESTROYER.getSize(),
                SUBMARINE.getSize(), SUBMARINE.getSize(), SUBMARINE.getSize(), SUBMARINE.getSize());
    }

    private Fleet(int... masts) {
        shipList = new ArrayList<>();
        for (int i : masts) {
            Ship ship = new Ship(i);
            shipList.add(ship);
        }
    }

    public static Fleet createDefaultFleet() {
        return new Fleet();
    }

    public final List<Ship> getShipList() {
        return shipList;
    }
}
