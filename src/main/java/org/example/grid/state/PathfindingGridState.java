package org.example.grid.state;

import org.example.grid.cell.BaseCell;
import org.example.grid.cell.CellType;
import org.example.grid.Point;
import org.example.grid.factory.CellModelFactory;
import org.example.grid.factory.GridStateMatrixFactory;

public class PathfindingGridState<T extends BaseCell> extends BaseGridState<T> implements Cloneable {

    private T[][] state;
    private Class<T> clazz;

    public PathfindingGridState(Class<T> clazz, int width, int height) {
        super(width, height);
        this.clazz = clazz;
        createStateMatrix();

        startingPointProperty().addListener(((obs, old, value) -> {
            getCell(value).setType(CellType.START_CELL);
            getCell(old).setType(CellType.NORMAL_CELL);
        }));
        destinationPointProperty().addListener(((obs, old, value) -> {
            getCell(value).setType(CellType.GOAL_CELL);
            getCell(old).setType(CellType.NORMAL_CELL);
        }));
    }

    private void createStateMatrix() {
        state = (T[][]) GridStateMatrixFactory.create(clazz, getWidth(), getHeight());
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Point coordinate = Point.of(x, y);
                state[x][y] = (T) CellModelFactory.create(clazz, coordinate);
                if (startingPointProperty().get().equals(coordinate)) {
                    state[x][y].setType(CellType.START_CELL);
                } else if (destinationPointProperty().get().equals(coordinate)) {
                    state[x][y].setType(CellType.GOAL_CELL);
                }
            }
        }
    }

    @Override
    public T getCell(Point coordinate) {
        return state[coordinate.getX()][coordinate.getY()];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        PathfindingGridState<T> gridStateClone = new PathfindingGridState<>(clazz, width.get(), height.get());

        gridStateClone.startingPointProperty().set((Point) this.startingPointProperty().getValue().clone());
        gridStateClone.destinationPointProperty().set((Point) this.destinationPointProperty().getValue().clone());

        for (int i = 0; i < gridStateClone.getWidth(); i++) {
            for (int j = 0; j < gridStateClone.getHeight(); j++) {
                T cellClone = (T) this.state[i][j].clone();
                gridStateClone.state[i][j] = cellClone;
            }
        }
        return gridStateClone;
    }
}
