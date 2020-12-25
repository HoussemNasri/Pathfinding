package org.example.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DijkstraCell extends AbstractCellModel {
    private final ObjectProperty<DijkstraCellType> cellType = new SimpleObjectProperty<>();

    public DijkstraCell(Point coordinate, DijkstraCellType cellType, boolean walkable) {
        super(coordinate, walkable);
        setType(cellType);
    }

    public DijkstraCell(Point coordinate, DijkstraCellType cellType) {
        this(coordinate, cellType, true);
    }

    public DijkstraCellType getType() {
        return cellType.get();
    }

    public void setType(DijkstraCellType cellType) {
        this.cellType.set(cellType);
    }

    public ObjectProperty<DijkstraCellType> cellTypeProperty() {
        return cellType;
    }

    public enum DijkstraCellType {
        NORMAL_CELL, WALL_CELL, START_CELL, GOAL_CELL, PATH_CELL
    }
}
