package game.shooting;

import model.preparing.Player;
import model.shooting.board.ShootingBoard;



interface ObservableGame {
    void addObserver(GameObserver gameObserver);
    void notifyObservers(ShotResults changes, ShootingBoard boardToShoot, Player currentPlayer);
}
