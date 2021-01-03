package org.example.grid.factory;

import org.example.grid.cell.BaseCell;
import org.example.grid.cell.AstarCell;
import org.example.grid.cell.DijkstraCell;
import org.example.grid.Point;

public class CellModelFactory {
    public static BaseCell create(Class<?> clazz, Point coordinate) {
        if (clazz.equals(AstarCell.class)) {
            return new AstarCell(coordinate);
        } else if (clazz.equals(DijkstraCell.class)) {
            return new DijkstraCell(coordinate);
        }
        return null;
    }
}
