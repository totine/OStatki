package placement.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Board {
    private static final int DEFAULT_COLUMN_COUNT = 10;
    private static final int DEFAULT_ROW_COUNT = 10;

    private final Map<Coordinates, Field> coordinatesToFieldsMap;

    private final int rows;
    private final int cols;

    public Board() {
        this.coordinatesToFieldsMap = new HashMap<>();
        this.rows = DEFAULT_COLUMN_COUNT;
        this.cols = DEFAULT_ROW_COUNT;
    }

    public boolean isEmpty() {
        return coordinatesToFieldsMap.isEmpty();
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    public void placeShip(Ship ship, int x, int y) {
        if (x < 0 || x > cols || y < 0 || y > rows) {
            return;
        }
        Coordinates mastCoordinates = new Coordinates(x, y);
        ship.setHeadCoordinates(mastCoordinates);

        for (int i = 0; i < ship.getMastNumber(); i++) {
            coordinatesToFieldsMap.put(mastCoordinates, new OccupiedField());
            surroundWithBuffer(mastCoordinates);
            mastCoordinates = ship.getDirection().nextCoordinates(mastCoordinates.getX(), mastCoordinates.getY());
        }

        ship.markAsPlaced();
    }

    private void surroundWithBuffer(Coordinates mastCoordinates) {
        Set<Coordinates> neighbours = getNeighbours(mastCoordinates);
        neighbours.forEach(coordinates -> coordinatesToFieldsMap.put(coordinates, new BufferField()));
    }

    private Set<Coordinates> getNeighbours(Coordinates coordinates) {
        HashSet<Coordinates> neighbours = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Coordinates neighbour = new Coordinates(coordinates.getX() + i, coordinates.getY() + j);
                neighbours.add(neighbour);
            }
        }

        neighbours.removeIf(isOutOfBoard());
        neighbours.removeIf(this::isFieldOccupied);
        return neighbours;
    }


    private Predicate<? super Coordinates> isOutOfBoard() {
        return coordinates -> coordinates.getY() < 0 || coordinates.getY() >= rows || coordinates.getX() < 0 || coordinates.getX() >= cols;
    }

    private void placeMast(Coordinates coordinates) {

    }

    public boolean isFieldOccupied(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        return coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField()).getClass().equals(OccupiedField.class);
    }

    public boolean isFieldOccupied(Coordinates coordinates) {
        return coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField()).getClass().equals(OccupiedField.class);
    }

    public boolean isFieldBuffer(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        return coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField()).getClass().equals(BufferField.class);
    }

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < rows ; i++) {
//            for (int j = 0; j < cols; j++) {
//
//            }
//        }
        StringBuilder sb = new StringBuilder();

        for (int i = -1; i < rows; i++) {
            for (int j = -1; j < cols; j++) {
                if (i == -1 && j != -1) {
                    sb.append(j);
                    sb.append("\t");
                }
                else if (j == -1 && i != -1) {
                    sb.append(i);
                    sb.append("\t");
                }
                else {
                    Coordinates coordinates = new Coordinates(j, i);
                    Field field = coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField());
                    sb.append(field.getMark());
                    sb.append("\t");
                }
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
