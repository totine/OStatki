package model.shooting.field.states;

import model.shooting.field.Field;
import model.shooting.field.HitResult;

public class DestroyedFieldState implements FieldState {
    private final Field field;

    public DestroyedFieldState(Field field) {
        this.field = field;
    }

    @Override
    public HitResult hit() {
        return HitResult.MISS;
    }

    @Override
    public void markDestroyed() {
        field.setState(this);
    }

    @Override
    public FieldStateName getName() {
        return FieldStateName.DESTROYED;
    }

    @Override
    public String getMark() {
        return "Z";
    }
}
