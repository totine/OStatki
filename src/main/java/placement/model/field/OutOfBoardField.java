package placement.model.field;

/**
 * Represents a notion of a field that is
 * impossible to exist. In other words a wrong field.
 */
public class OutOfBoardField implements Field {

    @Override
    public final FieldState getState() {
        return FieldState.OUTOFBOUND;
    }

    @Override
    public final String getMark() {
        return "";
    }

    @Override
    public final boolean isPlaceable() {
        return false;
    }
}
