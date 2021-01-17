package org.example.grid.cell;

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import org.example.grid.CellHistoryBrowser;
import org.example.grid.Point;

public abstract class BaseCell implements Cloneable {
    protected final Point coordinate;
    protected final ObjectProperty<CellType> cellType = new SimpleObjectProperty<>();

    public BaseCell(Point coordinate, CellType cellType) {
        this.coordinate = coordinate;
        setType(cellType);
    }

    public BaseCell(Point coordinate) {
        this(coordinate, CellType.NORMAL_CELL);
    }

    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * Check whether this cell can be explored or not. The default implementation allow exploring all types of cells, only WALL_CELL is not explorable
     */
    public boolean isWalkable() {
        return cellType.get() != CellType.WALL_CELL;
    }

    public CellType getType() {
        return cellType.get();
    }

    /**
     * Sets the type of the cell and record history
     */
    public void setType(CellType cellType) {
        if (cellType == getType())
            return;
        this.cellType.set(cellType);
    }

    public ObjectProperty<CellType> cellTypeProperty() {
        return cellType;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BaseCell baseCell = (BaseCell) o;
        return Objects.equals(coordinate, baseCell.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
