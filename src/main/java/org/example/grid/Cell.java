package org.example.grid;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;

import org.example.cell.AstarCost;

public class Cell {
    private Point2D coordinate;
    private ObjectProperty<CellKind> cellKind = new SimpleObjectProperty<>(CellKind.NORMAL_CELL);

    private ObjectProperty<AstarCost> cost = new SimpleObjectProperty<>(new AstarCost());

    public Cell(Point2D coordinate, CellKind cellKind) {
        this.coordinate = coordinate;
        this.cellKind.setValue(cellKind);
    }

    public Cell(Point2D coordinate) {
        this(coordinate, CellKind.NORMAL_CELL);
    }

    public Cell(int x, int y, CellKind cellKind) {
        this(new Point2D(x, y), cellKind);
    }

    public Cell(int x, int y) {
        this(x, y, CellKind.NORMAL_CELL);
    }

    public int getX() {
        return (int) coordinate.getX();
    }

    public int getY() {
        return (int) coordinate.getY();
    }

    public ObjectProperty<CellKind> cellKindProperty() {
        return cellKind;
    }

    public void setKind(CellKind cellKind) {
        this.cellKind.set(cellKind);
    }

    public boolean isWalkable() {
        return cellKind.get() != CellKind.WALL_CELL;
    }

    public Point2D getCoordinate() {
        return this.coordinate;
    }

    public void setCost(AstarCost cost) {
        this.cost.setValue(cost);
    }

    public AstarCost getCost() {
        return cost.get();
    }

    public ObjectProperty<AstarCost> costProperty() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", getX(), getY());
    }
}
