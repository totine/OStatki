package gui.utility;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public class ChangesObserver implements Observable {
    private List<InvalidationListener> listeners;

    public ChangesObserver(InvalidationListener listener) {
        this.listeners = new ArrayList<>();
        listeners.add(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        this.listeners = null;
    }
}
