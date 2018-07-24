package model.placement.fleet;

import model.Coordinates;
import model.placement.ship.DirectedShip;
import model.placement.ship.Direction;
import model.placement.ship.PlacedShip;
import model.placement.ship.ShipType;

public class FleetBuilder {
    private Fleet<PlacedShip> fleet;

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
