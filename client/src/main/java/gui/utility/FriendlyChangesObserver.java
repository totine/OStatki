package gui.utility;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class FriendlyChangesObserver implements Observable {
    private InvalidationListener listener;

    public FriendlyChangesObserver(InvalidationListener listener) {
        this.listener = listener;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        this.listener = listener;
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        this.listener = null;
    }
}
