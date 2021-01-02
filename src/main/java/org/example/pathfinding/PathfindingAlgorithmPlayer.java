package org.example.pathfinding;

public interface PathfindingAlgorithmPlayer extends AlgorithmPlayer {
    void step();
    SearchResult search();
}
