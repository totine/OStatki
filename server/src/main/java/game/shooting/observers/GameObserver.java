package game.shooting.observers;

import game.shooting.ShotResults;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;

public interface GameObserver {
    void update(ShotResults changes, ShootingBoard board, Player currentPlayer);
    void updateEndGame(Player winner);

    void updateWhosNext(Player currentPlayer);
}
