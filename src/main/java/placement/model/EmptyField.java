package placement.model;

public class EmptyField implements Field {
    @Override
    public final FieldState getState() {
        return FieldState.EMPTY;
    }

    @Override
    public final String getMark() {
        return "~";
    }
}
