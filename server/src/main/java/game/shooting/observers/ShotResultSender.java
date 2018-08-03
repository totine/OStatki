package game.shooting.observers;

import connection.communication.QueuesHandler;
import connection.serializers.JSONConverter;
import connection.utility.Command;
import connection.utility.CommandType;
import game.shooting.ShotResults;
import game.shooting.matchers.PlayerBoardMatcher;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;

public class ShotResultSender implements GameObserver {
    private final QueuesHandler queuesHandler;
    private final Player player;
    private final PlayerBoardMatcher boards;

    public ShotResultSender(QueuesHandler communicationRun, Player player, PlayerBoardMatcher boards) {
        this.queuesHandler = communicationRun;
        this.player = player;
        this.boards = boards;
    }

    private String boardsToSend() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMy Board\n\n");
        sb.append(boards.getOwnBoard(player).toString());
        sb.append("\n\nOpponent Board\n\n");
        sb.append(boards.getOpponentBoard(player).toStringOpponent());
        return sb.toString();
    }

    @Override
    public void update(ShotResults changes, ShootingBoard board, Player currentPlayer) {
        Command command;
        if (currentPlayer.equals(player)) {
            command = Command.withType(CommandType.SEND_OPPONENT_CHANGES, changes);
        } else {
            command = Command.withType(CommandType.SEND_MY_CHANGES, changes);
        }
        String serializedMessage = JSONConverter.convertToJSON(command);
        System.out.println(serializedMessage);
        queuesHandler.sendMessage(serializedMessage);
    }

    @Override
    public void updateEndGame(Player winner) {
        Command command = Command.withType(CommandType.END_GAME, winner);
        String s = JSONConverter.convertToJSON(command);
        queuesHandler.sendMessage(s);
    }
}
