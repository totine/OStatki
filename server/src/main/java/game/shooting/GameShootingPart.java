package game.shooting;

import game.shooting.matchers.PlayerBoardMatcher;
import game.shooting.matchers.PlayerToShotSupplierMatcher;
import game.shooting.observers.GameObserver;
import game.shooting.observers.ObservableGame;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;

import java.util.ArrayList;
import java.util.List;

public class GameShootingPart implements ObservableGame {
    private final List<GameObserver> observers;
    private final Judge judge;
    private final PlayerToShotSupplierMatcher shotSupplier;
    private final PlayerBoardMatcher playerBoardMatcher;

    private GameShootingPart(Judge judge, PlayerToShotSupplierMatcher hitSupplier,
                             PlayerBoardMatcher playerBoardMatcher) {
        observers = new ArrayList<>();
        this.judge = judge;
        this.shotSupplier = hitSupplier;
        this.playerBoardMatcher = playerBoardMatcher;
    }

    public static GameShootingPart create(Judge judge, PlayerBoardMatcher playerBoardMatcher,
                                          PlayerToShotSupplierMatcher playerShotSupplier) {
        playerBoardMatcher.addObserverToBoards(judge);

        return new GameShootingPart(judge, playerShotSupplier, playerBoardMatcher);
    }

    public void start() {
        while (!judge.isGameEnd()) {
            Player currentPlayer = judge.getCurrentPlayer();
            System.out.println("currentPlayer: " + currentPlayer);
            ShootingBoard boardToShoot = playerBoardMatcher.getOpponentBoard(currentPlayer);
            boardToShoot.hit(shotSupplier.getCoordinatesToShot(currentPlayer));
            notifyObservers(boardToShoot.getChanges(), boardToShoot, currentPlayer);
        }
        notifyObserversAboutGameEnd(judge.getCurrentPlayer());
    }

    private void notifyObserversAboutGameEnd(Player currentPlayer) {
        observers.forEach(observer -> observer.updateEndGame(currentPlayer));
    }

    @Override
    public void addObserver(GameObserver gameObserver) {
        observers.add(gameObserver);
    }

    @Override
    public void notifyObservers(ShotResults changes, ShootingBoard boardToShoot,
                                Player currentPlayer) {
        observers.forEach(observer -> observer.update(changes, boardToShoot, currentPlayer));

    }

}
