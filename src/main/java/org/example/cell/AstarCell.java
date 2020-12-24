package org.example.cell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AstarCell extends AbstractCellModel {
    private final ObjectProperty<CellType> cellType = new SimpleObjectProperty<>();
    private final ObjectProperty<AstarCost> cost = new SimpleObjectProperty<>();

    public AstarCell(Point coordinate, CellType cellType, boolean walkable) {
        super(coordinate, walkable);
        setType(cellType);
    }

    public AstarCell(Point coordinate, CellType cellType) {
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
     * Helpers to facilitate the access to cost object
     * */
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


    public enum CellType {
        NORMAL_CELL, WALL_CELL, START_CELL, GOAL_CELL, OPEN_CELL, CLOSE_CELL, PATH_CELL
    }
}

