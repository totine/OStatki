package model.placement.fleet;

import model.Coordinates;
import model.placement.ship.DirectedShip;
import model.placement.ship.Direction;
import model.placement.ship.PlacedShip;
import model.placement.ship.ShipType;

/**
 * Purpose of this class is to help with creating fleet.
 */
public class FleetBuilder {
    private final Fleet<PlacedShip> fleet;

    private FleetBuilder() {
        fleet = new Fleet<>();
    }

    public static FleetBuilder create() {
        return new FleetBuilder();
    }

    public FleetBuilder appendShip(Coordinates headCoordinates, ShipType shipType, Direction direction) {
        DirectedShip directedShip = new DirectedShip(shipType.getSize(), direction);
        PlacedShip placedShip = new PlacedShip(directedShip.createTempCoordinatesList(headCoordinates));
        fleet.add(placedShip);

        return this;
    }

    public Fleet<PlacedShip> build() {
        return fleet;
    }
}
