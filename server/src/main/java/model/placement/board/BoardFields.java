package model.placement.board;

import model.Coordinates;
import model.placement.field.Field;
import model.placement.field.OutOfBoardField;
import model.placement.field.EmptyField;
import model.placement.field.BufferField;
import model.placement.field.OccupiedField;

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
        return coordinates.getRowIndex() < 0 || coordinates.getRowIndex() >= rows
                || coordinates.getColumnIndex() < 0 || coordinates.getColumnIndex() >= cols;
    }

    void markAsBuffer(Coordinates coordinates) {
        coordinatesToFieldsMap.put(coordinates, new BufferField());
    }

    void markAsOccupied(Coordinates coordinates) {
        coordinatesToFieldsMap.put(coordinates, new OccupiedField());
    }
}
