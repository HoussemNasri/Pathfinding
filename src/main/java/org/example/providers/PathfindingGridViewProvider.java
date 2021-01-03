package org.example.providers;

import org.example.grid.view.grid.AstarGridviewWrapper;
import org.example.grid.view.grid.DijkstraGridviewWrapper;

public class PathfindingGridViewProvider {
    //TODO("Get cellSize for preferences")
    private static double cellSize = 40;

    private static AstarGridviewWrapper astarGridView = null;
    private static DijkstraGridviewWrapper dijkstraGridView = null;

    public static AstarGridviewWrapper getAstarGridView() {
        if (astarGridView == null) {
            astarGridView = new AstarGridviewWrapper(PathfindingGridStateProvider.getAstarPathfindingGridState(), cellSize);
        }
        return astarGridView;
    }

    public static DijkstraGridviewWrapper getDijkstraGridView() {
        if (dijkstraGridView == null) {
            dijkstraGridView = new DijkstraGridviewWrapper(PathfindingGridStateProvider.getDijkstraPathfindingGridState(), cellSize);
        }
        return dijkstraGridView;
    }
}
