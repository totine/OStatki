package model.shooting.field.states;

import model.shooting.field.Field;
import model.shooting.field.HitResult;

public class FloatingMastFieldState implements FieldState {
    private final Field field;

    public FloatingMastFieldState(Field field) {
        this.field = field;
    }

    @Override
    public HitResult hit() {
        field.setState(new DamageFieldState(field));
        return HitResult.HIT;
    }

    @Override
    public void markDestroyed() {
        field.setState(this);
    }

    @Override
    public FieldStateName getName() {
        return FieldStateName.FLOATING;
    }

    @Override
    public String getMark() {
        return "[]";
    }
}
