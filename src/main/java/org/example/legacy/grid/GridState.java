package org.example.legacy.grid;

import javafx.geometry.Point2D;

import org.example.exception.CoordinateOutOfBoundsException;
import org.example.utils.CoordinateUtils;

public class GridState {
    private int width;
    private int height;
    private Cell[][] cells;
    private Point2D startCellCoordinate;
    private Point2D goalCellCoordinate;
    private static final Point2D DEFAULT_START_CELL_COORDINATE = new Point2D(0, 0);

    public GridState(int width, int height, Point2D startCellCoordinate, Point2D goalCellCoordinate) {
        this.width = width;
        this.height = height;
        this.startCellCoordinate = startCellCoordinate;
        this.goalCellCoordinate = goalCellCoordinate;
        initCellMatrix();
    }

    public GridState(int width, int height, Point2D startCellCoordinate) {
        this(width, height, startCellCoordinate, new Point2D(width - 1, height - 1));
    }

    public GridState(int width, int height) {
        this(width, height, DEFAULT_START_CELL_COORDINATE, new Point2D(width - 1, height - 1));
    }

    /**
     * Initialize the state of all cells on the grid
     */
    private void initCellMatrix() {
        cells = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
                Point2D thisCoordinate = new Point2D(x, y);
                if (thisCoordinate.equals(startCellCoordinate)) {
                    cells[x][y].cellKindProperty().setValue(CellKind.START_CELL);
                }
                if (thisCoordinate.equals(goalCellCoordinate)) {
                    cells[x][y].cellKindProperty().setValue(CellKind.GOAL_CELL);
                }
            }
        }
    }

    /**
     * @return the number of cells on each row on the grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the number of rows on the grid
     */
    public int getHeight() {
        return height;
    }

    public Cell getCell(Point2D coordinate) {
        return getCell((int) coordinate.getX(), (int) coordinate.getY());
    }

    public Cell getCell(int x, int y) {
        if (!CoordinateUtils.isCoordinateCorrect(x, y, width, height))
            throw new CoordinateOutOfBoundsException(x, y);
        return cells[x][y];
    }

    public boolean isStartCell(Cell cell) {
        return isStartCell(cell.getCoordinate());
    }

    public boolean isGoalCell(Cell cell) {
        return isGoalCell(cell.getCoordinate());
    }

    public boolean isStartCell(Point2D coordinate) {
        return startCellCoordinate.equals(coordinate);
    }

    public boolean isGoalCell(Point2D coordinate) {
        return goalCellCoordinate.equals(coordinate);
    }

    public void updateStartCell(Point2D startCellCoordinate) {
        if (!CoordinateUtils.isCoordinateCorrect(startCellCoordinate, width, height))
            throw new CoordinateOutOfBoundsException(startCellCoordinate);
        this.startCellCoordinate = startCellCoordinate;
    }

    public void updateGoalCell(Point2D goalCellCoordinate) {
        this.goalCellCoordinate = goalCellCoordinate;
    }

    public Point2D getStartCellCoordinate() {
        return startCellCoordinate;
    }

    public Point2D getGoalCellCoordinate() {
        return goalCellCoordinate;
    }

    public void clear() {

    }
}
