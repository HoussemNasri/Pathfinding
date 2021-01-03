package org.example.pathfinding.update;

import java.util.HashSet;
import java.util.Set;

import org.example.cell.AstarCell;
import org.example.cell.state.PathfindingGridState;
import org.example.providers.PathfindingGridStateProvider;

public class AstarAlgorithmPlayer implements PathfindingAlgorithmPlayer {
    private static AstarAlgorithmPlayer INSTANCE = null;
    private boolean isPlaying = false;

    private final PathfindingGridState<AstarCell> gridState;
    private Set<AstarCell> openList = new HashSet<>();
    private Set<AstarCell> closedList = new HashSet<>();

    public AstarAlgorithmPlayer(PathfindingGridState<AstarCell> gridState) {
        this.gridState = gridState;
    }

    public AstarAlgorithmPlayer() {
        this(PathfindingGridStateProvider.getAstarPathfindingGridState());
    }

    @Override
    public void play() {

        isPlaying = true;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void pause() {

        isPlaying = false;
    }

    @Override
    public void reset() {

        pause();
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
