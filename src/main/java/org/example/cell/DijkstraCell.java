package org.example.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DijkstraCell extends AbstractCellModel {

    public DijkstraCell(Point coordinate, boolean walkable, CellType cellType) {
        super(coordinate, walkable, cellType);
    }

    public DijkstraCell(Point coordinate, CellType cellType) {
        this(coordinate, true, cellType);
    }

}
