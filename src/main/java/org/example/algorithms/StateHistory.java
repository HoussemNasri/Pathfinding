package org.example.algorithms;

import java.util.Stack;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

public class StateHistory<T> extends Stack<T> {
    private final ReadOnlyIntegerWrapper historySize = new ReadOnlyIntegerWrapper(0);

    public StateHistory() {
        historySize.set(0);
    }

    @Override
    public T push(T item) {
        incrementHistory();
        return super.push(item);
    }

    @Override
    public synchronized T pop() {
        decrementHistory();
        return super.pop();
    }

    private void incrementHistory() {
        historySize.set(historySize.get() + 1);
    }

    private void decrementHistory() {
        if (this.size() > 0) {
            historySize.set(historySize.get() - 1);
        }
    }

    public ReadOnlyIntegerProperty historySizeProperty() {
        return historySize.getReadOnlyProperty();
    }
}
