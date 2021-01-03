package org.example.cell.state;

import org.example.cell.AbstractCellModel;
import org.example.cell.AstarCell;
import org.example.cell.DijkstraCell;

public class GridStateMatrixFactory {
    public static AbstractCellModel[][] create(Class<? extends AbstractCellModel> clazz, int width, int height) {
        if (clazz.equals(AstarCell.class)) {
            return new AstarCell[width][height];
        } else if (clazz.equals(DijkstraCell.class)) {
            return new DijkstraCell[width][height];
        }

        return null;
    }
}
