package connection;

import java.util.HashMap;
import java.util.Map;

class ServiceManager {
    private Map<String, GameCommand> map;

    ServiceManager(Server server) {
        this.map = new HashMap<>();
        map.put("ADD_PLAYER", new AddPlayerGameCommand());
        map.put("ASK_FOR_FLEET", new AskForFleetGameCommand(server));
        map.put("CLIENT_READY", new ClientReadyGameCommand());
        map.put("PLACKI", new ClientReadyGameCommand());
    }

    GameCommand getCommand(String message) {
        if (message.startsWith("ADD_PLAYER")) {
            return map.get("ADD_PLAYER");
        }
        return map.get(message);
    }
}
