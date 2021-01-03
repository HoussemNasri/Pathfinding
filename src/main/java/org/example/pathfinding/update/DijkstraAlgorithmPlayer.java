package org.example.pathfinding.update;

import org.example.cell.DijkstraCell;
import org.example.cell.state.PathfindingGridState;
import org.example.providers.PathfindingGridStateProvider;

public class DijkstraAlgorithmPlayer implements PathfindingAlgorithmPlayer {
    private PathfindingGridState<DijkstraCell> gridState;

    public DijkstraAlgorithmPlayer(PathfindingGridState<DijkstraCell> gridState) {
        this.gridState = gridState;
    }

    public DijkstraAlgorithmPlayer() {
        this(PathfindingGridStateProvider.getDijkstraPathfindingGridState());
    }

    @Override
    public void play() {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void pause() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void stepIn() {

    }

    @Override
    public void stepOut() {

    }

    @Override
    public void step() {

    }

    @Override
    public SearchResult search() {
        return null;
    }
}
