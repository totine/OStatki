package placement.model;

public class Ship {
    private Coordinates headCoordinates;
    private final int mastNumber;
    private Direction direction;
    private boolean placed;

    public Ship(int mastCount, Direction direction) {
        this.mastNumber = mastCount;
        this.direction = direction;
    }

    public Ship(int i) {
        mastNumber = i;
    }

    public Coordinates getHeadCoordinates() {
        return headCoordinates;
    }

    public int getMastNumber() {
        return mastNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void markAsPlaced() {
        this.placed = true;
    }

    public void setHeadCoordinates(Coordinates headCoordinates) {
        this.headCoordinates = headCoordinates;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
