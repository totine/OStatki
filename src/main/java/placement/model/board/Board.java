package placement.model.board;

import placement.model.Coordinates;
import placement.model.ShipOnBufferException;
import placement.model.ShipOnOccupiedFieldException;
import placement.model.ShipOutOfBoardException;
import placement.model.field.*;
import placement.model.ship.Ship;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents battleship board in the context of ship placement (initial game phase).
 */
public final class Board {
    private static final int DEFAULT_COLUMN_COUNT = 10;
    private static final int DEFAULT_ROW_COUNT = 10;
    private final BoardFields boardFields;

    private final int rows;
    private final int cols;

    /**
     * creates a Board object with preset dimensions.
     */
    private Board(int rows, int cols) {
        boardFields = new BoardFields(rows, cols);
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Factory method for creating 10x10 board.
     *
     * @return Board of size 10x10
     */
    public static Board createDefaultBoard() {
        return new Board(DEFAULT_ROW_COUNT, DEFAULT_COLUMN_COUNT);
    }

    /**
     * Returns true if a board is not yet populated.
     *
     * @return true if a board is not yet populated.
     */
    boolean isEmpty() {
        return boardFields.isEmpty();
    }

    /**
     * Returns number of rows in the board (y-size).
     *
     * @return number of rows in the board.
     */
    public int rows() {
        return rows;
    }

    /**
     * Returns number of columns in the board (x-size).
     *
     * @return number of columns in the board.
     */
    public int cols() {
        return cols;
    }


    private FieldState getFieldState(Coordinates coordinates) {
        Field field = boardFields.getField(coordinates);
        return field.getState();
    }

    FieldState getFieldState(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        return getFieldState(coordinates);
    }

    /**
     * Places given ship on the board if it's possible.
     *
     * @param ship        ship to place.
     * @param coordinates coordinates of the ship's "head".
     * @see ShipOnBufferException
     * @see ShipOnOccupiedFieldException
     * @see ShipOutOfBoardException
     */
    public void placeShip(Ship ship, Coordinates coordinates) {
        try {
            Set<Coordinates> mastCoordinates = getMastCoordinates(ship, coordinates);
            mastCoordinates.forEach(coordinate -> {
                boardFields.markAsOccupied(coordinate);
                surroundWithBuffer(coordinate);
                ship.addPositionCoord(coordinate);
            });
            ship.markAsPlaced();

        } catch (ShipOutOfBoardException | ShipOnBufferException | ShipOnOccupiedFieldException e) {
            System.err.println(e.getMessage());
        }
    }

    public void placeShip(Ship ship, int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        placeShip(ship, coordinates);
    }

    private Set<Coordinates> getMastCoordinates(Ship ship, Coordinates coordinates)
            throws ShipOutOfBoardException, ShipOnBufferException, ShipOnOccupiedFieldException {
        Set<Coordinates> mastCoordinates = new HashSet<>();
        while (mastCoordinates.size() < ship.getMastNumber()) {
            checkIsAbleToPlace(coordinates);
            Coordinates nextCoord = ship.nextCoordinates();
            mastCoordinates.add(coordinates);
            coordinates = coordinates.add(nextCoord);
        }
        return mastCoordinates;
    }

    private void checkIsAbleToPlace(Coordinates coordinates)
            throws ShipOutOfBoardException, ShipOnBufferException, ShipOnOccupiedFieldException {
        Field field = boardFields.getField(coordinates);
        field.checkState();
    }

    private void surroundWithBuffer(Coordinates mastCoordinates) {
        Set<Coordinates> neighbours = getNeighboursToMarkAsBuffer(mastCoordinates);
        neighbours.forEach(boardFields::markAsBuffer);
    }

    private Set<Coordinates> getNeighboursToMarkAsBuffer(Coordinates coordinates) {
        HashSet<Coordinates> neighbours = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Coordinates neighbour = new Coordinates(
                        coordinates.getX() + i,
                        coordinates.getY() + j);
                neighbours.add(neighbour);
            }
        }
        neighbours.removeIf(this::isFieldOccupied);
        neighbours.removeIf(this::isOutOfBound);
        return neighbours;
    }

    private boolean isOutOfBound(Coordinates coordinates) {
        return getFieldState(coordinates).equals(FieldState.OUTOFBOUND);
    }

    private boolean isFieldOccupied(Coordinates coordinates) {
        return getFieldState(coordinates).equals(FieldState.OCCUPIED);
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
                    Field field = boardFields.getField(coordinates);
                    sb.append(field.getMark());
                    sb.append("\t");
                }
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
