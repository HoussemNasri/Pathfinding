package org.example.pathfinding;

import org.example.cell.AstarCell;
import org.example.cell.state.AbstractGridState;
import org.example.grid.GridState;

public class AstarAlgorithmPlayer implements PathfindingAlgorithmPlayer {
    private final AbstractGridState<AstarCell> gridState;

    public AstarAlgorithmPlayer(AbstractGridState<AstarCell> gridState) {
        this.gridState = gridState;
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
