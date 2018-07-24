package model.shooting.field.states;

import model.shooting.field.Field;
import model.shooting.field.HitResult;

public class DamageFieldState implements FieldState {
    private final Field field;

    DamageFieldState(Field field) {
        this.field = field;
    }

    @Override
    public HitResult hit() {
        field.setState(this);
        return HitResult.MISS;
    }

    @Override
    public void markDestroyed() {
        field.setState(new DestroyedFieldState(field));
    }

    @Override
    public FieldStateName getName() {
        return FieldStateName.DAMAGED;
    }

    @Override
    public String getMark() {
        return "X";
    }
}
