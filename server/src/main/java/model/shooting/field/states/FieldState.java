package model.shooting.field.states;

import model.shooting.field.HitResult;

public interface FieldState {
    HitResult hit();

    FieldStateName getName();

    void markDestroyed();

    String getMark();
}
