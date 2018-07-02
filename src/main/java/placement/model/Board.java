package placement.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
    private static final int DEFAULT_COLUMN_COUNT = 10;
    private static final int DEFAULT_ROW_COUNT = 10;

    private final Map<Coordinates, Field> coordinatesToFieldsMap;

    private final int rows;
    private final int cols;

    Board() {
        this.coordinatesToFieldsMap = new HashMap<>();
        this.rows = DEFAULT_COLUMN_COUNT;
        this.cols = DEFAULT_ROW_COUNT;
    }

    boolean isEmpty() {
        return coordinatesToFieldsMap.isEmpty();
    }

    int rows() {
        return rows;
    }

    int cols() {
        return cols;
    }

    void placeShip(Ship ship, int x, int y) {
        try {
            Coordinates coordinates = new Coordinates(x, y);
            Set<Coordinates> mastCoordinates = getMastCoordinates(ship, coordinates);
            mastCoordinates.forEach(coordinate -> {
                coordinatesToFieldsMap.put(coordinate, new OccupiedField());
                surroundWithBuffer(coordinate);
            });
            ship.setHeadCoordinates(new Coordinates(x, y));
            ship.markAsPlaced();

        } catch (ShipOutOfBoardException | ShipOnBufferException | ShipOnOccupiedFieldException e) {
            e.printStackTrace();
        }
    }

    private Set<Coordinates> getMastCoordinates(Ship ship, Coordinates coordinates) throws ShipOutOfBoardException, ShipOnBufferException, ShipOnOccupiedFieldException {
        Set<Coordinates> mastCoordinates = new HashSet<>();
        for (int i = 0; i < ship.getMastNumber(); i++) {
            checkCoordinates(coordinates);
            mastCoordinates.add(coordinates);
            coordinates = ship.getDirection().nextCoordinates(coordinates.getX(), coordinates.getY());
        }
        return mastCoordinates;
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

        neighbours.removeIf(this::isOutOfBoard);
        neighbours.removeIf(this::isFieldOccupied);
        return neighbours;
    }


    private boolean isOutOfBoard(Coordinates coordinates) {
        return coordinates.getY() < 0 || coordinates.getY() >= rows || coordinates.getX() < 0 || coordinates.getX() >= cols;
    }

    private void checkCoordinates(Coordinates coordinates) throws ShipOutOfBoardException, ShipOnBufferException, ShipOnOccupiedFieldException {
        if (isOutOfBoard(coordinates))
            throw new ShipOutOfBoardException();
        if (isFieldBuffer(coordinates))
            throw new ShipOnBufferException();
        if (isFieldOccupied(coordinates))
            throw new ShipOnOccupiedFieldException();

    }

    private boolean isFieldBuffer(Coordinates coordinates) {
        return coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField()).getClass().equals(BufferField.class);
    }


    boolean isFieldOccupied(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        return isFieldOccupied(coordinates);
    }

    private boolean isFieldOccupied(Coordinates coordinates) {
        return coordinatesToFieldsMap.getOrDefault(coordinates, new EmptyField()).getState().equals(FieldState.OCCUPIED);
    }

    boolean isFieldBuffer(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        return isFieldBuffer(coordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = -1; i < rows; i++) {
            for (int j = -1; j < cols; j++) {
                if (i == -1 && j != -1) {
                    sb.append(j);
                    sb.append("\t");
                } else if (j == -1 && i != -1) {
                    sb.append(i);
                    sb.append("\t");
                } else {
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

    public void placeShip(Ship ship, Coordinates coordinates) {
        placeShip(ship, coordinates.getX(), coordinates.getY());
    }
}
