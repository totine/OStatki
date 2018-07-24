package model.shooting.field.states;

import model.shooting.field.Field;
import model.shooting.field.HitResult;

public class SeenFieldState implements FieldState {
    private final Field field;

    public SeenFieldState(Field field) {
        this.field = field;
    }

    @Override
    public HitResult hit() {
        field.setState(this);
        return HitResult.MISS;
    }

    @Override
    public void markDestroyed() {
        field.setState(this);
    }

    @Override
    public FieldStateName getName() {
        return FieldStateName.SEEN;
    }

    @Override
    public String getMark() {
        return "o";
    }

}
