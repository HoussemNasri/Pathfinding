package org.example.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class AbstractCellModel {
    protected Point coordinate;
    protected boolean walkable;
    protected final ObjectProperty<CellType> cellType = new SimpleObjectProperty<>();
    protected CellType previousCellType;

    public AbstractCellModel(Point coordinate, boolean walkable, CellType cellType) {
        this.coordinate = coordinate;
        this.walkable = walkable;
        setType(cellType);
        setPreviousCellType(cellType);
        cellTypeProperty().addListener((obs, old, neww) -> previousCellType = old);
    }

    public AbstractCellModel(Point coordinate, boolean walkable) {
        this(coordinate, walkable, CellType.NORMAL_CELL);
    }

    public AbstractCellModel(Point coordinate) {
        this(coordinate, true);
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
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

    public CellType getPreviousCellType() {
        return previousCellType;
    }

    private void setPreviousCellType(CellType cellType) {
        this.previousCellType = cellType;
    }
}
