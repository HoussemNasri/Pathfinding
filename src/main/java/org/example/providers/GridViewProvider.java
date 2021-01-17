package org.example.providers;

import org.example.grid.view.grid.AstarGridviewWrapper;
import org.example.grid.view.grid.DijkstraGridviewWrapper;

public class GridViewProvider {
    //TODO("Get cellSize for preferences")
    private static double cellSize = 70;

    private static AstarGridviewWrapper astarGridView = null;
    private static DijkstraGridviewWrapper dijkstraGridView = null;

    public static AstarGridviewWrapper getAstarGridView() {
        if (astarGridView == null) {
            astarGridView = new AstarGridviewWrapper(GridStateProvider.getAstarPathfindingGridState(), cellSize);
        }
        return astarGridView;
    }

    public static DijkstraGridviewWrapper getDijkstraGridView() {
        if (dijkstraGridView == null) {
            dijkstraGridView = new DijkstraGridviewWrapper(GridStateProvider.getDijkstraPathfindingGridState(), cellSize);
        }
        return dijkstraGridView;
    }
}
