package org.example.algorithms.astar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import org.example.algorithms.HeuristicUtils;
import org.example.algorithms.PathfindingAlgorithmPlayer;
import org.example.algorithms.SearchResult;
import org.example.algorithms.StateHistory;
import org.example.grid.Point;
import org.example.grid.cell.AstarCell;
import org.example.grid.cell.CellType;
import org.example.grid.state.PathfindingGridState;
import org.example.providers.GridStateProvider;
import org.example.utils.CoordinateUtils;

public class AstarAlgorithmPlayer implements PathfindingAlgorithmPlayer<AstarState> {
    public static final int NON_DIAGONAL_DISTANCE = 10;
    private BooleanProperty isPlaying = new SimpleBooleanProperty(false);

    private AstarState state;
    private StateHistory<AstarState> history;

    public AstarAlgorithmPlayer(PathfindingGridState<AstarCell> gridState) {
        this.state = new AstarState(gridState);
        this.history = new StateHistory<>();
    }

    public AstarAlgorithmPlayer() {
        this(GridStateProvider.getAstarPathfindingGridState());
    }

    public List<AstarCell> getNeighbours(AstarCell cell) {
        List<AstarCell> result = new ArrayList<>();

        int x = cell.getCoordinate().getX();
        int y = cell.getCoordinate().getY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighbourX = x + i;
                int neighbourY = y + j;
                Point neighbourCoordinate = Point.of(neighbourX, neighbourY);

                boolean isNeighbourOnCorner = Math.abs(i) + Math.abs(j) == 2;
                boolean isNeighbourOnCenter = i == 0 && j == 0;

                if (isNeighbourOnCenter || isNeighbourOnCorner) {
                    continue;
                }
                if (CoordinateUtils.notInBounds(neighbourCoordinate, state.getGridState().getWidth(), state.getGridState().getHeight())) {
                    continue;
                }
                if (!state.getGridState().getCell(neighbourCoordinate).isWalkable()) {
                    continue;
                }

                result.add(state.getGridState().getCell(neighbourCoordinate));
            }
        }
        return result;
    }

    public PathfindingGridState<AstarCell> getGridState() {
        return state.getGridState();
    }

    @Override
    public void play() {
        AnimationTimer timer = new AnimationTimer() {
            private long start = -1;
            @Override
            public void handle(long now) {
                if (start == -1 || now - start >= 160000000) {
                    start = now;
                    if (isPlaying.get()) {
                        stepIn();
                    }
                }
            }
        };
        timer.start();
        isPlaying.setValue(true);
    }

    @Override
    public BooleanProperty isPlayingProperty() {
        return isPlaying;
    }

    @Override
    public void pause() {

        isPlaying.setValue(false);
    }

    @Override
    public void reset() {
        for (int x = 0; x < getGridState().getWidth(); x++) {
            for (int y = 0; y < getGridState().getHeight(); y++) {
                AstarCell thisCell = getGridState().getCell(Point.of(x, y));
                if (thisCell.isWalkable() && thisCell.getType() != CellType.START_CELL && thisCell.getType() != CellType.GOAL_CELL) {
                    thisCell.setType(CellType.NORMAL_CELL);
                }
            }
        }
        getGridState().getStartingCell().setType(CellType.START_CELL);
        getGridState().getDestinationCell().setType(CellType.GOAL_CELL);
        state.openList().clear();
        state.closedList().clear();
        pause();
    }

    @Override
    public void stepIn() {
        //Save State
        //saveState();

        //Do change
        PriorityQueue<AstarCell> openList = state.openList();
        HashSet<AstarCell> closedList = state.closedList();

        if (openList.isEmpty()) {
            openList.add(getGridState().getStartingCell());
        }

        AstarCell currentCell = openList.poll();
        closedList.add(currentCell);

        assert currentCell != null;
        System.out.println(currentCell.gCost() + " " + currentCell.hCost() + " " + currentCell.fCost());

        currentCell.setType(CellType.CLOSE_CELL);

        if (currentCell.equals(getGridState().getDestinationCell())) {
            System.out.println("Found Path!");
            isPlaying.setValue(false);
            while (currentCell != null) {
                currentCell.setType(CellType.PATH_CELL);
                currentCell = currentCell.getParent();
            }
            return;
        }

        List<AstarCell> neighbours = getNeighbours(currentCell);
        for (AstarCell thisNeighbour : neighbours) {
            if (closedList.contains(thisNeighbour)) {
                continue;
            }
            int newGCost = currentCell.gCost() + NON_DIAGONAL_DISTANCE;
            if (openList.contains(thisNeighbour)) {
                if (thisNeighbour.gCost() > newGCost) {
                    thisNeighbour.setGCost(newGCost);
                    thisNeighbour.setParent(currentCell);
                }
            } else {
                thisNeighbour.setGCost(newGCost);
                thisNeighbour.setParent(currentCell);
                thisNeighbour.setHCost(HeuristicUtils.manhattanDistance(thisNeighbour, getGridState().getDestinationCell()));
                openList.add(thisNeighbour);
                thisNeighbour.setType(CellType.OPEN_CELL);
            }
        }
    }

    @Override
    public void stepOut() {
        //Restore last state before change
        restoreState();
    }

    @Override
    public SearchResult search() {
        return null;
    }

    @Override
    public void saveState() {
        try {
            AstarState astarStateClone = (AstarState) this.state.clone();
            history.push(astarStateClone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restoreState() {
        if (history.size() < 1)
            return;

        AstarState restoredState = history.pop();

        this.state.setOpenList(restoredState.openList());
        this.state.setClosedList(restoredState.closedList());

        for (int i = 0; i < restoredState.getGridState().getWidth(); i++) {
            for (int j = 0; j < restoredState.getGridState().getHeight(); j++) {
                AstarCell restoredCell = restoredState.getGridState().getCell(Point.of(i, j));
                AstarCell mainCell = state.getGridState().getCell(Point.of(i, j));
                mainCell.setType(restoredCell.getType());
                mainCell.setCost(restoredCell.getCost());
            }
        }
    }

    @Override
    public StateHistory<AstarState> getStateHistory() {
        return history;
    }
}
