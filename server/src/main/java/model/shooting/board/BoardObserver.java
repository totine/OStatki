package model.shooting.board;

import model.Coordinates;

public interface BoardObserver {

    void update(ShootingBoard board, Coordinates coordinates);
}
