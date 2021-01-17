package org.example.providers;

import org.example.algorithms.astar.AstarAlgorithmPlayer;
import org.example.algorithms.disjkstra.DijkstraAlgorithmPlayer;

public class AlgorithmPlayerProvider {
    private static AstarAlgorithmPlayer astarAlgorithmPlayer = null;
    private static DijkstraAlgorithmPlayer dijkstraAlgorithmPlayer = null;

    public static AstarAlgorithmPlayer getAstarAlgorithmPlayer() {
        if (astarAlgorithmPlayer == null) {
            astarAlgorithmPlayer = new AstarAlgorithmPlayer();
        }
        return astarAlgorithmPlayer;
    }

    public static DijkstraAlgorithmPlayer getDijkstraAlgorithmPlayer() {
        if (dijkstraAlgorithmPlayer == null) {
            dijkstraAlgorithmPlayer = new DijkstraAlgorithmPlayer();
        }
        return dijkstraAlgorithmPlayer;
    }
}
