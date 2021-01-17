package org.example.algorithms.astar;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.example.grid.cell.AstarCell;
import org.example.grid.state.PathfindingGridState;

public class AstarState implements Cloneable, Comparator<AstarCell> {
    private final PathfindingGridState<AstarCell> gridState;

    private PriorityQueue<AstarCell> openList;
    private HashSet<AstarCell> closedList;

    public AstarState(PathfindingGridState<AstarCell> gridState) {
        this.gridState = gridState;
        this.openList = new PriorityQueue<>(this);
        this.closedList = new HashSet<>();
    }

    public PathfindingGridState<AstarCell> getGridState() {
        return gridState;
    }

    public PriorityQueue<AstarCell> openList() {
        return openList;
    }

    public HashSet<AstarCell> closedList() {
        return closedList;
    }

    public void setOpenList(PriorityQueue<AstarCell> openList) {
        this.openList = openList;
    }

    public void setClosedList(HashSet<AstarCell> closedList) {
        this.closedList = closedList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        AstarState astarStateClone = new AstarState((PathfindingGridState<AstarCell>) gridState.clone());
        PriorityQueue<AstarCell> cellPriorityQueue = new PriorityQueue<>();
        this.openList.stream().map(cell -> {
            try {
                return (AstarCell) cell.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(cellPriorityQueue::add);
        astarStateClone.openList = (PriorityQueue<AstarCell>) cellPriorityQueue;
        astarStateClone.closedList = (HashSet<AstarCell>) this.closedList.clone();
        return astarStateClone;
    }

    @Override
    public int compare(AstarCell o1, AstarCell o2) {
        return o1.getCost().compareTo(o2.getCost());
    }
}
