package game;

import game.shooting.ShotResults;
import game.shooting.observers.GameObserver;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;

public class GamePrinter implements GameObserver {
    @Override
    public void update(ShotResults changes, ShootingBoard board, Player currentPlayer) {
        System.out.println(board);
    }

    @Override
    public void updateEndGame(Player winner) {
        System.out.println("Game is over");
        System.out.println("The winner is " + winner);
    }
}
