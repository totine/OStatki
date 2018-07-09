package placement.model.ship;


/**
 * Represents a ship, that is undirected and not placed in the context of placing ships on a board.
 * Knows its mast number.
 */
public class UndirectedShip implements Ship {
    private final int mastNumber;

    public UndirectedShip(int mastNumber) {
        this.mastNumber = mastNumber;
    }

    @Override
    public final boolean isPlaced() {
        return false;
    }


    @Override
    public final DirectedShip direct(Direction direction) {
        return new DirectedShip(mastNumber, direction);
    }
}
