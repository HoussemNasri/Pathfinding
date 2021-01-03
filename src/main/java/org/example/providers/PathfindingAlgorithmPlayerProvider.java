package org.example.providers;

import org.example.algorithms.aStar.AstarAlgorithmPlayer;
import org.example.algorithms.disjkstra.DijkstraAlgorithmPlayer;

public class PathfindingAlgorithmPlayerProvider {
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
