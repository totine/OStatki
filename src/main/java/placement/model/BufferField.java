package placement.model;

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
