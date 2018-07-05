package placement.model.board;

import placement.model.Coordinates;
import placement.model.field.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFields {
    private final Map<Coordinates, Field> coordinatesToFieldsMap;
    private final int rows;
    private final int cols;

    public BoardFields(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        coordinatesToFieldsMap = new HashMap<>();
    }

    public boolean isEmpty() {
        return coordinatesToFieldsMap.isEmpty();
    }

    public Field getField(Coordinates coordinates) {
        if (isCoordOutOfBound(coordinates)) {
            return new OutOfBoardField();
        }
        return coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField());
    }

    private boolean isCoordOutOfBound(Coordinates coordinates) {
        return coordinates.getY() < 0 || coordinates.getY() >= rows
                || coordinates.getX() < 0 || coordinates.getX() >= cols;
    }

    public void markAsBuffer(Coordinates coordinates) {
        coordinatesToFieldsMap.put(coordinates, new BufferField());
    }

    public void markAsOccupied(Coordinates coordinates) {
        coordinatesToFieldsMap.put(coordinates, new OccupiedField());
    }
}
