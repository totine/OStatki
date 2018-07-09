package placement.model.board;

import placement.model.Coordinates;
import placement.model.field.Field;
import placement.model.field.OutOfBoardField;
import placement.model.field.EmptyField;
import placement.model.field.BufferField;
import placement.model.field.OccupiedField;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents fields states on board
 * coordinates are related to field state
 */
class BoardFields {
    private final Map<Coordinates, Field> coordinatesToFieldsMap;
    private final int rows;
    private final int cols;

    BoardFields(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        coordinatesToFieldsMap = new HashMap<>();
    }

    boolean isEmpty() {
        return coordinatesToFieldsMap.isEmpty();
    }

    Field getField(Coordinates coordinates) {
        if (isCoordOutOfBound(coordinates)) {
            return new OutOfBoardField();
        }
        return coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField());
    }

    private boolean isCoordOutOfBound(Coordinates coordinates) {
        return coordinates.getY() < 0 || coordinates.getY() >= rows
                || coordinates.getX() < 0 || coordinates.getX() >= cols;
    }

    void markAsBuffer(Coordinates coordinates) {
        coordinatesToFieldsMap.put(coordinates, new BufferField());
    }

    void markAsOccupied(Coordinates coordinates) {
        coordinatesToFieldsMap.put(coordinates, new OccupiedField());
    }
}
