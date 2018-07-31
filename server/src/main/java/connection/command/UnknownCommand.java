package connection.command;

import com.google.gson.JsonObject;
import connection.communication.QueuesHandler;

import java.util.Objects;

public class UnknownCommand implements GameCommand {
    private final QueuesHandler communicationRun;

    UnknownCommand(QueuesHandler communicationRun) {
        this.communicationRun = communicationRun;
    }

    @Override
    public void execute() {

    }

    @Override
    public void setValue(JsonObject value) {

    }

    @Override
    public String toString() {
        return "UnknownCommand{"
                + "communicationRun=" + communicationRun
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnknownCommand)) {
            return false;
        }
        UnknownCommand that = (UnknownCommand) o;
        return Objects.equals(communicationRun, that.communicationRun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(communicationRun);
    }
}
