package org.example.algorithms;

import org.example.algorithms.astar.StateManager;

public interface PathfindingAlgorithmPlayer<T> extends BasePlayer, StateManager<T> {
    SearchResult search();
}
