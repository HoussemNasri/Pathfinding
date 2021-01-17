package org.example.algorithms.astar;

import org.example.algorithms.StateHistory;

/**
 * @param <T> The state type
 * */
public interface StateManager<T> {
    void saveState();
    void restoreState();
    StateHistory<T> getStateHistory();

}
