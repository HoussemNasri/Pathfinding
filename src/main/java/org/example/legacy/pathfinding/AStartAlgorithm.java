package org.example.legacy.pathfinding;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;

import org.example.algorithms.aStar.AstarCost;
import org.example.legacy.grid.CellKind;
import org.example.legacy.grid.GridState;
import org.example.legacy.style.AStarCellStyler;
import org.example.legacy.style.CellStyler;
import org.example.utils.CoordinateUtils;

public class AStartAlgorithm implements PathfindingAlgorithm{
    public static final int SQUARE_DISTANCE = 10;
    private GridState gridState;
    private AStartCell[][] cells;
    private Set<AStartCell> openList = new HashSet<>();
    private Set<AStartCell> closedList = new HashSet<>();

    private CellStyler cellStyler;

    public AStartAlgorithm(GridState gridState, CellStyler cellStyler) {
        this.gridState = gridState;
        this.cellStyler = cellStyler;
        cells = new AStartCell[gridState.getWidth()][gridState.getHeight()];
        for (int x = 0; x < gridState.getWidth(); x++) {
            for (int y = 0; y < gridState.getHeight(); y++) {
                AStartCell cell = CellMapper.getInstance().mapTo(gridState.getCell(x, y));
                cells[x][y] = cell;
            }
        }
    }

    public AStartAlgorithm(GridState gridState) {
        this(gridState, AStarCellStyler.getInstance());
    }

    private int heuristic(AStartCell cell) {
        AStartCell goalCell = getCell(gridState.getGoalCellCoordinate());
        int dx = Math.abs(cell.getCell().getX() - goalCell.getCell().getX());
        int dy = Math.abs(cell.getCell().getY() - goalCell.getCell().getY());

        //There is no specific reason why I chose "10", I tried different values
        //and "10" was the most promising
        return 10 * (dx + dy);
    }

    @Override
    public void search() {
        AStartCell startCell = getCell(gridState.getStartCellCoordinate());
        openList.add(startCell);

        while (openList.size() > 0) {
            if (step())
                break;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean step() {
        if (openList.isEmpty()) {
            AStartCell startCell = getCell(gridState.getStartCellCoordinate());
            openList.add(startCell);
        }
        AStartCell current = getMostPromisingCell();
        openList.remove(current);
        closedList.add(current);
        current.getCell().setKind(CellKind.CLOSED_CELL);

        //we have reached the goal cell
        if (current.getCell().getCoordinate().equals(gridState.getGoalCellCoordinate())) {
            drawPath(current);
            return true;
        }

        int currentX = current.getCell().getX();
        int currentY = current.getCell().getY();

        exploreNeighbours(currentX, currentY);
        return false;
    }

    private void drawPath(AStartCell goal) {
        AStartCell current = goal;
        while (current != null) {
            current.getCell().setKind(CellKind.PATH_CELL);
            current = current.getParent();
        }
    }

    public void exploreNeighbours(int x, int y) {
        AStartCell parentCell = getCell(x, y);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;

                boolean isCorner = Math.abs(i) + Math.abs(j) == 2;
                boolean isCenter = i == 0 && j == 0;

                if (isCenter || isCorner)
                    continue;
                if (!CoordinateUtils.isCoordinateCorrect(newX, newY, gridState.getWidth(), gridState.getHeight()) || !getCell(newX, newY).isWalkable()) {
                    continue;
                }

                AStartCell cell = getCell(newX, newY);
                if (closedList.contains(cell))
                    continue;

                int newGCost = parentCell.gCost() + SQUARE_DISTANCE;
                if (openList.contains(cell)) {
                    if (cell.gCost() > newGCost) {
                        cell.setgCost(newGCost);
                        cell.setParent(parentCell);
                    }
                } else {
                    cell.setgCost(newGCost);
                    openList.add(cell);
                    cell.setParent(parentCell);
                }
                cell.getCell().setKind(CellKind.OPEN_CELL);
                cell.sethCost(heuristic(cell));
                cell.getCell().setCost(new AstarCost(cell.gCost(), cell.hCost()));
            }
        }
    }

    /**
     * Will return the cell with the lowest fCost from the openList
     */
    private AStartCell getMostPromisingCell() {
        AStartCell lowest = null;
        for (AStartCell cell : openList) {
            if (lowest == null || lowest.fCost() > cell.fCost()) {
                lowest = cell;
            } else if (lowest.fCost() == cell.fCost() && lowest.hCost() > cell.hCost()) {
                lowest = cell;
            }
        }
        //if null then openlist must be empty
        return lowest;
    }

    public AStartCell getCell(int x, int y) {
        return cells[x][y];
    }

    public AStartCell getCell(Point2D coordinate) {
        return getCell((int) coordinate.getX(), (int) coordinate.getY());
    }
}
