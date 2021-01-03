package org.example.grid.factory;

import org.example.grid.cell.BaseCell;
import org.example.grid.cell.AstarCell;
import org.example.grid.cell.DijkstraCell;

public class GridStateMatrixFactory {
    public static BaseCell[][] create(Class<? extends BaseCell> clazz, int width, int height) {
        if (clazz.equals(AstarCell.class)) {
            return new AstarCell[width][height];
        } else if (clazz.equals(DijkstraCell.class)) {
            return new DijkstraCell[width][height];
        }

        return null;
    }
}
