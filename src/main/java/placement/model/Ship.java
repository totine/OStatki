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

    Ship(int mastCount, Direction direction) {
        this.mastNumber = mastCount;
        this.direction = direction;
        directionCoordinates = new ArrayList<>();
    }

    Ship(int i) {
        mastNumber = i;
        directionCoordinates = new ArrayList<>();
    }


    int getMastNumber() {
        return mastNumber;
    }

    Direction getDirection() {
        return direction;
    }

    public boolean isPlaced() {
        return placed;
    }

    void markAsPlaced() {
        this.placed = true;
    }

    void setHeadCoordinates(Coordinates headCoordinates) {
        this.headCoordinates = headCoordinates;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    void addDirectionCoord(Coordinates coordinates) {

        directionCoordinates.add(coordinates);
    }
}
