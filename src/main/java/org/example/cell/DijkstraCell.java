package org.example.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DijkstraCell extends AbstractCellModel {

    public DijkstraCell(Point coordinate, CellType cellType) {
        super(coordinate, cellType);
    }

    public DijkstraCell(Point coordinate) {
        this(coordinate, CellType.NORMAL_CELL);
    }
}
