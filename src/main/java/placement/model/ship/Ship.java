package placement.model.ship;

public interface Ship {
    boolean isPlaced();
    DirectedShip direct(Direction direction);
}
