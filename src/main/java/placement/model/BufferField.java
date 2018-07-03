package placement.model;

/**
 * Represents a field that is adjacent to some ship.
 */
public class BufferField implements Field {
    @Override
    public final FieldState getState() {
        return FieldState.BUFFER;
    }

    @Override
    public final String getMark() {
        return "⚓";
    }
}
