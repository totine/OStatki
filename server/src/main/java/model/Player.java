package model;

import java.util.Objects;

public class Player {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player create(String name) {
        return new Player(name);
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
}