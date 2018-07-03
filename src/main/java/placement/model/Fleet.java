package placement.model;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    List<Ship> shipList;

    private static final int BATTLESHIP = 4;
    private static final int CRUISER = 3;
    private static final int DESTROYER = 2;
    private static final int SUBMARINE = 1;

    public Fleet() {
        this(BATTLESHIP,
                CRUISER, CRUISER,
                DESTROYER, DESTROYER, DESTROYER,
                SUBMARINE, SUBMARINE, SUBMARINE, SUBMARINE);
    }

    private Fleet(int... masts) {
        shipList = new ArrayList<>();
        for (int i : masts) {
            Ship ship = new Ship(i);
            shipList.add(ship);
        }

    }

    public List<Ship> getShipList() {
        return shipList;
    }
}
