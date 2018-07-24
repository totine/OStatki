package model.shooting.field.states;

import model.shooting.field.Field;
import model.shooting.field.HitResult;

public class OutOfBoardState implements FieldState {
    private final Field field;

    public OutOfBoardState(Field field) {
        this.field = field;
    }

    @Override
    public HitResult hit() {
        return HitResult.OUT_OF_BOARD;
    }

    @Override
    public void markDestroyed() {
        field.setState(this);
    }

    @Override
    public FieldStateName getName() {
        return FieldStateName.OUT_OF_BOARD;
    }

    @Override
    public String getMark() {
        return "";
    }
}
