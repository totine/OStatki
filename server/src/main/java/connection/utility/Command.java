package connection.utility;

import java.util.Objects;

public class Command<T> {
    private CommandType command;
    private T value;

    private Command(CommandType command, T value) {
        this.command = command;
        this.value = value;
    }

    public static <T> Command withType(CommandType command, T value) {
        return new Command<>(command, value);
    }

    public static Command empty(CommandType command) {
        return new Command<>(command, null);
    }

    @Override
    public String toString() {
        return "Command{"
                + "command="
                + command
                + ", value="
                + value
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Command)) {
            return false;
        }
        Command<?> command1 = (Command<?>) o;
        return command == command1.command
                && Objects.equals(value, command1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, value);
    }
}
