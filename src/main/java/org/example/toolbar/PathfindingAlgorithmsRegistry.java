package org.example.toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.example.algorithms.PathfindingAlgorithmModel;

public class PathfindingAlgorithmsRegistry {
    private final static List<PathfindingAlgorithmModel> algorithms = new ArrayList<>();

    static {
        algorithms.add(new PathfindingAlgorithmModel("A*", "A* Search Algorithm"));
        algorithms.add(new PathfindingAlgorithmModel("D*", "D* Search Algorithm"));
        algorithms.add(new PathfindingAlgorithmModel("Dijkstra", "Dijkstra's Shortest Path First algorithm"));
        algorithms.add(new PathfindingAlgorithmModel("Breadth-first search"));
        algorithms.add(new PathfindingAlgorithmModel("Best First Search"));
        algorithms.add(new PathfindingAlgorithmModel("Jump Point Search"));
    }

    public static List<PathfindingAlgorithmModel> getPathfindingAlgorithms() {
        return Collections.unmodifiableList(algorithms);
    }

    public static PathfindingAlgorithmModel lookup(PathfindingAlgorithm algorithm) {
        return algorithms.get(lookupIndex(algorithm));
    }

    public static int lookupIndex(PathfindingAlgorithm algorithm) {
        return Arrays.asList(PathfindingAlgorithm.values()).indexOf(algorithm);
    }
}
