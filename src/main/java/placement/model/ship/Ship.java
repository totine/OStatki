package placement.model.ship;

/**
 * Interface that represents Ship in the context of ship placement
 */
public interface Ship {
    boolean isPlaced();

    DirectedShip direct(Direction direction);
}
