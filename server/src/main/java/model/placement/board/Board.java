package model.placement.board;

import model.Coordinates;

import model.placement.field.Field;
import model.placement.field.FieldState;

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
    public boolean isEmpty() {
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

    /**
     * this method returns state of the field which has coordinates as in passed parameter.
     * @param coordinates
     * coordinates parameters is used to check if that place on board is taken,
     * or if ship can be placed on that spot
     * @return
     * returns state of the field which can be OCCUPIED, BUFFER, EMPTY, OUT_OF_BOUND
     */
    public FieldState getFieldState(Coordinates coordinates) {
        Field field = boardFields.getField(coordinates);
        return field.getState();
    }

    /**
     * this method returns true or false whether or not mast
     * can be placed on the board.
     * @param mastCoordinates
     * this parameter is there to check
     * if mast on that coordinates on board can be placed
     * @return
     * method returns true if mast can be place on that
     * coordinates or false if not
     */
    public boolean mastCanBePlaced(Coordinates mastCoordinates) {
        Field field = boardFields.getField(mastCoordinates);
        return field.isFreeToPlace();
    }

    /**
     * method simply places mast on certain place on board.
     * @param coordinates
     * this parameter is there to place on mast on that certain position on board.
     */
    public void placeMast(Coordinates coordinates) {
        boardFields.markAsOccupied(coordinates);
        surroundWithBuffer(coordinates);
    }

    private void surroundWithBuffer(Coordinates mastCoordinates) {
        Set<Coordinates> neighbours = getNeighboursToMarkAsBuffer(mastCoordinates);
        neighbours.forEach(boardFields::markAsBuffer);
    }

    private Set<Coordinates> getNeighboursToMarkAsBuffer(Coordinates coordinatesToSurroundWithBuffer) {
        HashSet<Coordinates> neighbours = getAllNeighboursCoordinates(coordinatesToSurroundWithBuffer);
        neighbours.removeIf(this::isFieldOccupied);
        neighbours.removeIf(this::isOutOfBound);
        return neighbours;

    }

    private HashSet<Coordinates> getAllNeighboursCoordinates(Coordinates coordinatesToSurroundWithBuffer) {
        HashSet<Coordinates> neighbours = new HashSet<>();
        for (int toAddToX = -1; toAddToX <= 1; toAddToX++) {
            for (int toAddToY = -1; toAddToY <= 1; toAddToY++) {
                Coordinates toAdd = Coordinates.create(toAddToX, toAddToY);
                Coordinates neighbour = coordinatesToSurroundWithBuffer.add(toAdd);
                neighbours.add(neighbour);
            }
        }
        neighbours.remove(coordinatesToSurroundWithBuffer);
        return neighbours;
    }

    private boolean isOutOfBound(Coordinates coordinates) {
        return getFieldState(coordinates).equals(FieldState.OUT_OF_BOUND);
    }

    private boolean isFieldOccupied(Coordinates coordinates) {
        return getFieldState(coordinates).equals(FieldState.OCCUPIED);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int leftMargin = -1;
        int topMargin = -1;

        for (int i = topMargin; i < rows; i++) {
            for (int j = leftMargin; j < cols; j++) {
                if (i == topMargin && j != leftMargin) {
                    sb.append(j);
                    sb.append("\t");
                } else if (j == leftMargin && i != topMargin) {
                    sb.append(i);
                    sb.append("\t");
                } else {
                    Coordinates coordinates = Coordinates.create(j, i);
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
