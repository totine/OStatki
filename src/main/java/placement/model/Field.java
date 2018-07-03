package placement.model;

/**
 * represents a field in the context of placing ships on the board.
 */
public interface Field {
    FieldState getState();

    String getMark();
}
