package model.shooting.board;

import model.Coordinates;
import model.shooting.field.HitResult;

interface ObservableBoard {
    void addObserver(BoardObserver observer);
    void notifyObservers(Coordinates coordinates, HitResult hitResult);

}
