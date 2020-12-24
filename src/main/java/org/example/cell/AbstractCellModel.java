package org.example.cell;

import javafx.geometry.Point2D;

public abstract class AbstractCellModel {
    protected Point coordinate;
    protected boolean walkable;

    public AbstractCellModel(Point coordinate, boolean walkable) {
        this.coordinate = coordinate;
        this.walkable = walkable;
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
}
