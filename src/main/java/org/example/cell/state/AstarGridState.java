package org.example.cell.state;

import org.example.cell.AstarCell;
import org.example.cell.Point;

public class AstarGridState extends AbstractGridState<AstarCell> {

    private AstarCell[][] state;

    public AstarGridState(int width, int height) {
        super(width, height);
        createStateMatrix();
    }

    private void createStateMatrix() {
        state = new AstarCell[getWidth()][getHeight()];
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                state[x][y] = new AstarCell(Point.of(x, y));
            }
        }
    }

    @Override
    public AstarCell getCell(Point coordinate) {
        return state[coordinate.getX()][coordinate.getY()];
    }
}
