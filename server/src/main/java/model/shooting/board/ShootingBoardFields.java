package model.shooting.board;

import model.Coordinates;
import model.shooting.field.Field;
import model.shooting.field.states.FieldStateName;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ShootingBoardFields {
    private final Map<Coordinates, Field> coordinatesToFieldsMap;
    private final int rows;
    private final int cols;

    public ShootingBoardFields(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        coordinatesToFieldsMap = new HashMap<>();
    }


    public void addFloatingMastField(Coordinates coordinates) {
        coordinatesToFieldsMap.put(coordinates, Field.withShip());
    }

    public Field get(Coordinates coordinates) {
        if (isCoordinateOutOfBoard(coordinates)) {
            return Field.outOfBoard();
        }
        Field field = coordinatesToFieldsMap.getOrDefault(coordinates, Field.empty());
        coordinatesToFieldsMap.put(coordinates, field);
        return field;
    }

    public boolean hasNoFloatingMasts() {
        return coordinatesToFieldsMap.values()
                .stream()
                .noneMatch(field -> field.getStateName().equals(FieldStateName.FLOATING));
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private boolean isCoordinateOutOfBoard(Coordinates coordinates) {
        return (coordinates.getColumnIndex() < 0 || coordinates.getColumnIndex() > rows - 1
                || coordinates.getRowIndex() < 0 || coordinates.getRowIndex() > cols - 1);
    }

    HashSet<Coordinates> getAllNeighboursCoordinates(Coordinates coordinatesToSurroundWithBuffer) {
        HashSet<Coordinates> neighbours = new HashSet<>();
        for (int toAddToX = -1; toAddToX <= 1; toAddToX++) {
            for (int toAddToY = -1; toAddToY <= 1; toAddToY++) {
                Coordinates toAdd = Coordinates.create(toAddToX, toAddToY);
                Coordinates neighbour = coordinatesToSurroundWithBuffer.add(toAdd);
                neighbours.add(neighbour);
            }
        }
        neighbours.remove(coordinatesToSurroundWithBuffer);
        neighbours.removeIf(coord -> !get(coord).getStateName().equals(FieldStateName.EMPTY));
        return neighbours;
    }
}
