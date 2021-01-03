package org.example.grid.cell;

import org.example.grid.Point;

public class DijkstraCell extends BaseCell {

    public DijkstraCell(Point coordinate, CellType cellType) {
        super(coordinate, cellType);
    }

    public DijkstraCell(Point coordinate) {
        this(coordinate, CellType.NORMAL_CELL);
    }
}
