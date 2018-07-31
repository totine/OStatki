package game.shooting.matchers;

import model.preparing.Player;
import game.shooting.Judge;
import model.shooting.board.ShootingBoard;

import java.util.HashMap;
import java.util.Map;

public class PlayerBoardMatcher {
    private final Map<Player, ShootingBoard> playerOwnBoards;
    private final Map<Player, ShootingBoard> playerOpponentBoards;

    private PlayerBoardMatcher(Map<Player, ShootingBoard> playerOwnBoards,
                               Map<Player, ShootingBoard> playerOpponentBoards) {
        this.playerOwnBoards = playerOwnBoards;
        this.playerOpponentBoards = playerOpponentBoards;
    }

    public static PlayerBoardMatcher create(Player playerA, ShootingBoard boardA,
                                            Player playerB, ShootingBoard boardB) {
        Map<Player, ShootingBoard> playerOwnBoards = matchPlayersWithBoards(playerA, playerB, boardA, boardB);
        Map<Player, ShootingBoard> playerOpponentBoard = matchPlayersWithBoards(playerA, playerB, boardB, boardA);
        return new PlayerBoardMatcher(playerOwnBoards, playerOpponentBoard);
    }

    public void addObserverToBoards(Judge judge) {
        playerOwnBoards.values().forEach(board -> board.addObserver(judge));
    }

    public ShootingBoard getOpponentBoard(Player player) {
        return playerOpponentBoards.get(player);
    }

    public ShootingBoard getOwnBoard(Player player) {
        return playerOwnBoards.get(player);
    }

    private static Map<Player, ShootingBoard> matchPlayersWithBoards(Player playerA, Player playerB,
                                                                     ShootingBoard boardA, ShootingBoard boardB) {
        Map<Player, ShootingBoard> boards = new HashMap<>();
        boards.put(playerA, boardA);
        boards.put(playerB, boardB);
        return boards;
    }
}
