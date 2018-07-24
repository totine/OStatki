package model.shooting.field;

import model.shooting.field.states.EmptyFieldState;
import model.shooting.field.states.FieldState;
import model.shooting.field.states.FieldStateName;
import model.shooting.field.states.FloatingMastFieldState;
import model.shooting.field.states.OutOfBoardState;

public class Field {
    private FieldState fieldState;

    private Field() {
        this.fieldState = new EmptyFieldState(this);
    }

    public static Field withShip() {
        Field field = new Field();
        FieldState fieldState = new FloatingMastFieldState(field);
        field.setState(fieldState);
        return field;
    }

    public static Field empty() {
        return new Field();
    }

    public static Field outOfBoard() {
        Field field = new Field();
        FieldState fieldState = new OutOfBoardState(field);
        field.setState(fieldState);
        return field;
    }

    public HitResult hit() {
        return fieldState.hit();
    }

    public void markDestroyed() {
        fieldState.markDestroyed();
    }

    public void setState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    public FieldStateName getStateName() {
        return fieldState.getName();
    }

    public String getMark() {
        return this.fieldState.getMark();
    }
}
