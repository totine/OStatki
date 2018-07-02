package placement.model;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    List<Ship> shipList;

    public Fleet() {
        this(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
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
