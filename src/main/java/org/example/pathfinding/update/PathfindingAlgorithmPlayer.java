package org.example.pathfinding.update;

public interface PathfindingAlgorithmPlayer extends AlgorithmPlayer {
    void step();
    SearchResult search();
}
