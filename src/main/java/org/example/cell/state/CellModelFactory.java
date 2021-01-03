package org.example.cell.state;

import org.example.cell.AbstractCellModel;
import org.example.cell.AstarCell;
import org.example.cell.DijkstraCell;
import org.example.cell.Point;

public class CellModelFactory {
    public static AbstractCellModel create(Class<?> clazz, Point coordinate) {
        if (clazz.equals(AstarCell.class)) {
            return new AstarCell(coordinate);
        } else if (clazz.equals(DijkstraCell.class)) {
            return new DijkstraCell(coordinate);
        }
        return null;
    }
}
