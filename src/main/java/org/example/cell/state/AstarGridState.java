package org.example.cell.state;

import org.example.cell.AstarCell;
import org.example.cell.CellType;
import org.example.cell.Point;

public class AstarGridState extends AbstractGridState<AstarCell> {

    private AstarCell[][] state;

    public AstarGridState(int width, int height) {
        super(width, height);
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
        state = new AstarCell[getWidth()][getHeight()];
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Point coordinate = Point.of(x, y);
                state[x][y] = new AstarCell(coordinate);
                if (startingPointProperty().get().equals(coordinate)) {
                    state[x][y].setType(CellType.START_CELL);
                } else if (destinationPointProperty().get().equals(coordinate)) {
                    state[x][y].setType(CellType.GOAL_CELL);
                }
            }
        }
    }

    @Override
    public AstarCell getCell(Point coordinate) {
        return state[coordinate.getX()][coordinate.getY()];
    }
}
