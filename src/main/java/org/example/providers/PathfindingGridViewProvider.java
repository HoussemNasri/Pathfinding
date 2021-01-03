package org.example.providers;

import org.example.grid.view.grid.AstarGridView;
import org.example.grid.view.grid.DijkstraGridView;

public class PathfindingGridViewProvider {
    //TODO("Get cellSize for preferences")
    private static double cellSize = 40;

    private static AstarGridView astarGridView = null;
    private static DijkstraGridView dijkstraGridView = null;

    public static AstarGridView getAstarGridView() {
        if (astarGridView == null) {
            astarGridView = new AstarGridView(PathfindingGridStateProvider.getAstarPathfindingGridState(), cellSize);
        }
        return astarGridView;
    }

    public static DijkstraGridView getDijkstraGridView() {
        if (dijkstraGridView == null) {
            dijkstraGridView = new DijkstraGridView(PathfindingGridStateProvider.getDijkstraPathfindingGridState(), cellSize);
        }
        return dijkstraGridView;
    }
}
