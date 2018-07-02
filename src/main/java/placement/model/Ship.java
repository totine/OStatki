package placement.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private Coordinates headCoordinates;
    private final int mastNumber;
    private Direction direction;
    private boolean placed;

    public List<Coordinates> getDirectionCoordinates() {
        return directionCoordinates;
    }

    private List<Coordinates> directionCoordinates;

    public Ship(int mastCount, Direction direction) {
        this.mastNumber = mastCount;
        this.direction = direction;
        directionCoordinates = new ArrayList<>();
    }

    public Ship(int i) {
        mastNumber = i;
        directionCoordinates = new ArrayList<>();
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

    public void addDirectionCoord(Coordinates coordinates) {

        directionCoordinates.add(coordinates);
    }
}
