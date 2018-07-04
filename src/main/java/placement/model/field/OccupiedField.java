package placement.model.field;

/**
 * Represents a field already taken by some ship.
 */
public class OccupiedField implements Field {
    @Override
    public final FieldState getState() {
        return FieldState.OCCUPIED;
    }

    @Override
    public final String getMark() {
        return "⛴";
    }
}
