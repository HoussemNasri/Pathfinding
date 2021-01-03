package org.example.toolbar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseButton;

import de.saxsys.mvvmfx.ViewModel;
import org.example.grid.cell.BaseCell;
import org.example.grid.cell.CellType;
import org.example.grid.state.AbstractGridState;
import org.example.grid.listener.CellClickedListener;
import org.example.grid.listener.CellDraggedOverListener;
import org.example.grid.listener.cellevent.CellClickedEvent;
import org.example.grid.listener.cellevent.CellDraggedOverEvent;
import org.example.algorithms.AlgorithmPlayer;
import org.example.algorithms.PathfindingAlgorithmPlayer;
import org.example.providers.PathfindingAlgorithmPlayerProvider;

public class MyToolbarViewModel implements ViewModel, CellClickedListener, CellDraggedOverListener, AlgorithmPlayer {
    private final ObjectProperty<GridTool> selectedGridTool = new SimpleObjectProperty<>(GridTool.WALL_TOOL);
    private final ObjectProperty<PathfindingAlgorithm> selectedAlgorithm = new SimpleObjectProperty<>(PathfindingAlgorithm.A_STAR);

    private PathfindingAlgorithmPlayer pathfindingAlgorithmPlayer;

    public MyToolbarViewModel() {
        pathfindingAlgorithmPlayer = PathfindingAlgorithmPlayerProvider.getAstarAlgorithmPlayer();
        selectedAlgorithm.addListener((observable, oldValue, newValue) -> {
            if (newValue == PathfindingAlgorithm.A_STAR) {
                pathfindingAlgorithmPlayer = PathfindingAlgorithmPlayerProvider.getAstarAlgorithmPlayer();
                System.out.println(pathfindingAlgorithmPlayer);
            } else if (newValue == PathfindingAlgorithm.DIJKSTRA) {
                pathfindingAlgorithmPlayer = PathfindingAlgorithmPlayerProvider.getDijkstraAlgorithmPlayer();
                System.out.println(pathfindingAlgorithmPlayer);
            }
        });
    }

    public ObjectProperty<GridTool> selectedGridToolProperty() {
        return selectedGridTool;
    }

    public GridTool getSelectedGridTool() {
        return selectedGridTool.get();
    }

    @Override
    public void onCellClicked(CellClickedEvent clickedEvent) {
        AbstractGridState<?> gridState = clickedEvent.getGridState();
        BaseCell clickedCell = clickedEvent.getClickedCell();
        MouseButton mouseButton = clickedEvent.getMouseButton();

        if (getSelectedGridTool() == GridTool.HOME_MARKER_TOOL) {
            doHomeMarkerTool(gridState, clickedCell);
        } else if (getSelectedGridTool() == GridTool.DESTINATION_MARKER_TOOL) {
            doDestinationMarkerTool(gridState, clickedCell);
        } else if (getSelectedGridTool() == GridTool.WALL_TOOL) {
            doWallTool(clickedCell, mouseButton);
        }
    }

    @Override
    public void onCellDraggedOver(CellDraggedOverEvent cellDraggedOverEvent) {
        BaseCell draggedOverCell = cellDraggedOverEvent.getDraggedOverCell();
        MouseButton mouseButton = cellDraggedOverEvent.getMouseButton();

        if (getSelectedGridTool() == GridTool.WALL_TOOL) {
            doWallTool(draggedOverCell, mouseButton);
        }
    }

    private void doWallTool(BaseCell cell, MouseButton mouseButton) {
        if (cell.getType() != CellType.NORMAL_CELL && cell.getType() != CellType.WALL_CELL) {
            return;
        }
        if (mouseButton == MouseButton.PRIMARY && cell.getType() == CellType.NORMAL_CELL) {
            cell.setType(CellType.WALL_CELL);
        } else if (mouseButton == MouseButton.SECONDARY && cell.getType() == CellType.WALL_CELL) {
            cell.setType(CellType.NORMAL_CELL);
        }
    }

    private void doDestinationMarkerTool(AbstractGridState<?> gridState, BaseCell cell) {
        if (cell.getType() != CellType.NORMAL_CELL)
            return;
        gridState.setDestinationPoint(cell.getCoordinate());
    }

    private void doHomeMarkerTool(AbstractGridState<?> gridState, BaseCell cell) {
        if (cell.getType() != CellType.NORMAL_CELL)
            return;
        gridState.setStartingPoint(cell.getCoordinate());
    }

    public ObjectProperty<PathfindingAlgorithm> selectedAlgorithmProperty() {
        return selectedAlgorithm;
    }

    public PathfindingAlgorithm getSelectedAlgorithm() {
        return selectedAlgorithm.get();
    }

    @Override
    public void play() {
        pathfindingAlgorithmPlayer.play();
    }

    @Override
    public boolean isPlaying() {
        return pathfindingAlgorithmPlayer.isPlaying();
    }

    @Override
    public void pause() {
        pathfindingAlgorithmPlayer.pause();
    }

    @Override
    public void reset() {
        pathfindingAlgorithmPlayer.reset();
    }

    @Override
    public void stepIn() {
        pathfindingAlgorithmPlayer.stepIn();
    }

    @Override
    public void stepOut() {
        pathfindingAlgorithmPlayer.stepOut();
    }

    public void playPause() {
        if (isPlaying()) {
            pause();
        } else {
            play();
        }
    }
}