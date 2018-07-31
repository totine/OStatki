package connection.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;
import model.Coordinates;

public class ShotCommand implements GameCommand {
    private final QueuesHandler communicationRun;
    private Coordinates coordinatesToQueue;

    ShotCommand(QueuesHandler communicationRun) {
        this.communicationRun = communicationRun;
    }

    @Override
    public void execute() {
        try {
            communicationRun.addCoordinateToQueue(coordinatesToQueue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setValue(JsonObject value) {
        Gson gson = new Gson();
        coordinatesToQueue = gson.fromJson(value, Coordinates.class);
    }
}
