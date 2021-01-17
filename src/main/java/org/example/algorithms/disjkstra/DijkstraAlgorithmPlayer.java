package org.example.algorithms.disjkstra;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import org.example.algorithms.PathfindingAlgorithmPlayer;
import org.example.algorithms.SearchResult;
import org.example.algorithms.StateHistory;
import org.example.grid.cell.DijkstraCell;
import org.example.grid.state.PathfindingGridState;
import org.example.providers.GridStateProvider;

public class DijkstraAlgorithmPlayer implements PathfindingAlgorithmPlayer<DijkstraState> {
    private BooleanProperty isPlaying = new SimpleBooleanProperty(false);

    private PathfindingGridState<DijkstraCell> gridState;

    public DijkstraAlgorithmPlayer(PathfindingGridState<DijkstraCell> gridState) {
        this.gridState = gridState;
    }

    public DijkstraAlgorithmPlayer() {
        this(GridStateProvider.getDijkstraPathfindingGridState());
    }

    @Override
    public void play() {

        isPlaying.setValue(true);
    }

    @Override
    public BooleanProperty isPlayingProperty() {
        return null;
    }

    @Override
    public void pause() {

        isPlaying.setValue(false);
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
    public SearchResult search() {
        return null;
    }

    @Override
    public void saveState() {

    }

    @Override
    public void restoreState() {

    }

    @Override
    public StateHistory<DijkstraState> getStateHistory() {
        return null;
    }
}
