package connection;

import model.Player;

public class AddPlayerGameCommand implements GameCommand {
    private static final String ADD_PLAYER_COMMAND = "ADD_PLAYER:";
    private static final String RANDOM_PLAYER_NAME = "RandomPlayer";
    private static final String COMMAND_SEPARATOR = ":";

    @Override
    public void execute(String message) {
        String playerName = returnNameFromMessage(message);
        Player currentPlayer = Player.create(playerName);
        printIfNoNamePassed(currentPlayer);
    }

    private String returnNameFromMessage(String message) {
        if (checkIfMessageIsValid(message)) {
            return message.substring(message.indexOf(COMMAND_SEPARATOR) + 1);
        } else {
            return RANDOM_PLAYER_NAME;
        }
    }

    private boolean checkIfMessageIsValid(String message) {
        return message.contains(ADD_PLAYER_COMMAND);
    }

    private void printIfNoNamePassed(Player currentPlayer) {
        if (currentPlayer.equals(Player.create(RANDOM_PLAYER_NAME))) {
            System.out.println("Player did not specify his/her name.");
        }
    }

}
