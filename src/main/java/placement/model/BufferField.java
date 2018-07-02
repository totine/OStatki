package placement.model;

public class BufferField implements Field {
    @Override
    public FieldState getState() {
        return FieldState.BUFFER;
    }

    @Override
    public String getMark() {
        return "⚓";
    }
}
