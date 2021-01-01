package org.example.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AstarCell extends AbstractCellModel {
    private final ObjectProperty<AstarCost> cost = new SimpleObjectProperty<>();

    public AstarCell(Point coordinate, CellType cellType) {
        super(coordinate, cellType != CellType.WALL_CELL, cellType);
    }

    public AstarCell(Point coordinate) {
        this(coordinate, CellType.NORMAL_CELL);
    }

    public AstarCost getCost() {
        return cost.get();
    }

    public void setCost(AstarCost cost) {
        this.cost.set(cost);
    }

    public ObjectProperty<AstarCost> costProperty() {
        return cost;
    }

    /**
     * Helpers to speed up accessing cost object
     */
    public int gCost() {
        return getCost().gCost();
    }

    public void setGCost(int gCost) {
        getCost().setGCost(gCost);
    }

    public int hCost() {
        return getCost().hCost();
    }

    public void setHCost(int hCost) {
        getCost().setHCost(hCost);
    }

    public int fCost() {
        return getCost().fCost();
    }
}

