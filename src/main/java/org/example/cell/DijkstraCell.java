package org.example.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DijkstraCell extends AbstractCellModel {
    private final ObjectProperty<CellType> cellType = new SimpleObjectProperty<>();

    public DijkstraCell(Point coordinate, CellType cellType, boolean walkable) {
        super(coordinate, walkable);
        setType(cellType);
    }

    public DijkstraCell(Point coordinate, CellType cellType) {
        this(coordinate, cellType, true);
    }

    public CellType getType() {
        return cellType.get();
    }

    public void setType(CellType cellType) {
        this.cellType.set(cellType);
    }

    public ObjectProperty<CellType> cellTypeProperty() {
        return cellType;
    }

    public enum CellType {
        NORMAL_CELL, WALL_CELL, START_CELL, GOAL_CELL, PATH_CELL
    }
}
