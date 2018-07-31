package game.shooting.observers;

import game.shooting.ShotResults;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;



public interface ObservableGame {
    void addObserver(GameObserver gameObserver);
    void notifyObservers(ShotResults changes, ShootingBoard boardToShoot, Player currentPlayer);
}
