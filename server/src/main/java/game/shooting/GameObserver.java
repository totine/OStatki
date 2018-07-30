package game.shooting;

import model.preparing.Player;
import model.shooting.board.ShootingBoard;

interface GameObserver {
    void update(ShotResults changes, ShootingBoard board, Player currentPlayer);
}
