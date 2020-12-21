package org.example.pathfinding;

public interface PathfindingAlgorithm {
    /**
     * @return true if we reached our goal cell
     */
    boolean step();

    void search();
}
