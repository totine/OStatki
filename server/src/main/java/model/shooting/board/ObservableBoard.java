package model.shooting.board;

import model.Coordinates;

public interface ObservableBoard {
    void addObserver(BoardObserver observer);
    void notifyObservers(Coordinates coordinates);

}
