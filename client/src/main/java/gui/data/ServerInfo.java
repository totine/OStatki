package gui.data;

import java.util.Objects;

public class ServerInfo {
    private final String host;
    private final int port;

    private ServerInfo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static ServerInfo create(String host, int port) {
        return new ServerInfo(host, port);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return host + ":" + port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServerInfo)) {
            return false;
        }
        ServerInfo that = (ServerInfo) o;
        return port == that.port
                && Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {

        return Objects.hash(host, port);
    }
}
