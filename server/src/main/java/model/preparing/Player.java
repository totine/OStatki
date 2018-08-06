package model.preparing;

import java.util.Objects;

public class Player {
    private final String name;
    private final int id;

    private Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static Player create(String name, int id) {
        return new Player(name, id);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
