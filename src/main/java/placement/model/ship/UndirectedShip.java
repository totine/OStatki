package placement.model.ship;

public class UndirectedShip implements Ship {
    private final int mastNumber;

    public UndirectedShip(int mastNumber) {
        this.mastNumber = mastNumber;
    }

    @Override
    public boolean isPlaced() {
        return false;
    }


    @Override
    public DirectedShip direct(Direction direction) {
        return new DirectedShip(mastNumber, direction);
    }
}
