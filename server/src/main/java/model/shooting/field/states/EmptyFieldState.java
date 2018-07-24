package model.shooting.field.states;

import model.shooting.field.Field;
import model.shooting.field.HitResult;

public class EmptyFieldState implements FieldState {
    private final Field field;

    public EmptyFieldState(Field field) {
        this.field = field;
    }

    @Override
    public HitResult hit() {
        field.setState(new SeenFieldState(field));
        return HitResult.MISS;
    }

    @Override
    public void markDestroyed() {
        field.setState(this);
    }

    @Override
    public FieldStateName getName() {
        return FieldStateName.EMPTY;
    }

    @Override
    public String getMark() {
        return ".";
    }

}
