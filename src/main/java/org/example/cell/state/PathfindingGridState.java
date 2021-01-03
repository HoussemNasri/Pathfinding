package org.example.cell.state;

import org.example.cell.AbstractCellModel;
import org.example.cell.AstarCell;
import org.example.cell.CellType;
import org.example.cell.Point;

public class PathfindingGridState<T extends AbstractCellModel> extends AbstractGridState<T> {

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
}
