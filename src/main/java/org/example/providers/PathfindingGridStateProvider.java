package org.example.providers;

import org.example.cell.AstarCell;
import org.example.cell.DijkstraCell;
import org.example.cell.state.PathfindingGridState;

public class PathfindingGridStateProvider {
    //TODO("Get width and height from preferences")
    private static int width = 20;
    private static int height = 10;

    private static PathfindingGridState<AstarCell> astarState = null;
    private static PathfindingGridState<DijkstraCell> dijkstraState = null;

    public static PathfindingGridState<AstarCell> getAstarPathfindingGridState() {
        if (astarState == null) {
            astarState = new PathfindingGridState<>(AstarCell.class, width, height);
        }
        return astarState;
    }

    public static PathfindingGridState<DijkstraCell> getDijkstraPathfindingGridState() {
        if (dijkstraState == null) {
            dijkstraState = new PathfindingGridState<>(DijkstraCell.class, width, height);
        }
        return dijkstraState;
    }
}
