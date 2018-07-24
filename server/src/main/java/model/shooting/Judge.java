package model.shooting;

import model.Coordinates;
import model.shooting.board.BoardObserver;
import model.shooting.board.ShootingBoard;

public class Judge implements BoardObserver {
    private boolean isEnd;

    @Override
    public void update(ShootingBoard board, Coordinates coordinates) {
        isEnd = board.areAllMastsDestroyed();
    }

    public boolean isEnd() {
        return isEnd;
    }
}
