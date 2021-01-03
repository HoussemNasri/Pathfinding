package org.example.grid.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import org.example.algorithms.aStar.AstarCost;
import org.example.grid.Point;

public class AstarCell extends BaseCell {
    private final ObjectProperty<AstarCost> cost = new SimpleObjectProperty<>(new AstarCost());

    public AstarCell(Point coordinate, CellType cellType) {
        super(coordinate, cellType);
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

