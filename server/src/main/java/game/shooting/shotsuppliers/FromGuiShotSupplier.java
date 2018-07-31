package game.shooting.shotsuppliers;

import connection.communication.QueuesHandler;
import model.Coordinates;

public class FromGuiShotSupplier implements ShotSupplier {
    private final QueuesHandler communicationRun;

    public FromGuiShotSupplier(QueuesHandler communicationRun) {
        this.communicationRun = communicationRun;
    }

    @Override
    public Coordinates getCoordinatesToShot() {
        communicationRun.activate();
        Coordinates coordinatesToSend = null;
        try {
            coordinatesToSend = communicationRun.getCoordinates();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        communicationRun.deactivate();
        return coordinatesToSend;
    }
}
