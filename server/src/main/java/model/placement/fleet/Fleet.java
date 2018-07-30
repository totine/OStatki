package model.placement.fleet;

import model.placement.ship.Ship;
import model.placement.ship.ShipType;
import model.placement.ship.UndirectedShip;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static model.placement.ship.ShipType.ONE_MAST;
import static model.placement.ship.ShipType.TWO_MAST;
import static model.placement.ship.ShipType.THREE_MAST;
import static model.placement.ship.ShipType.FOUR_MAST;

/**
 * Represents a fleet of ships.
 *
 * @param <T> fleet can be generic - it is able to store different types of Ships.
 */
public final class Fleet<T extends Ship> {
    private final List<T> shipList;

    /**
     * creates Fleet instance.
     */
    public Fleet() {
        shipList = new ArrayList<>();
    }

    /**
     * this method creates Fleet instance which consists of UndirectedShips
     * ship which is Undirected knows only about it's size but not about its
     * direction.
     *
     * @return Fleet which is sort of list which consists of UndirectedShips
     */
    public static Fleet<UndirectedShip> createDefaultFleet() {
        Fleet<UndirectedShip> fleet = new Fleet<>();
        ShipType[] shipTypes = {FOUR_MAST,
                THREE_MAST, THREE_MAST,
                TWO_MAST, TWO_MAST, TWO_MAST,
                ONE_MAST, ONE_MAST, ONE_MAST, ONE_MAST};
        for (ShipType shipType : shipTypes) {
            UndirectedShip ship = new UndirectedShip(shipType.getSize());
            fleet.add(ship);
        }
        return fleet;
    }

    /**
     * this method returns list of ships
     *
     * @return returns List of ships.
     */
    public List<T> getShipList() {
        return shipList;
    }

    /**
     * this method adds ship object to the Fleet object which it is invoked.
     *
     * @param ship this parameter is used to append ship to the Fleet object.
     */
    public void add(T ship) {
        shipList.add(ship);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fleet<?> fleet = (Fleet<?>) o;
        return Objects.equals(shipList, fleet.shipList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipList);
    }

    @Override
    public String toString() {
        return "Fleet{"
                + "shipList="
                + shipList.toString()
                + '}';
    }
}
