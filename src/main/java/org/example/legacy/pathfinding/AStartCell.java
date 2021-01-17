package org.example.legacy.pathfinding;

import org.example.legacy.grid.Cell;

public class AStartCell {
    private final Cell cell;

    //distance from starting cell
    private int gCost;
    //distance from goal cell
    private int hCost;

    private AStartCell parent = null;

    public AStartCell(Cell cell, int gCost, int hCost) {
        this.cell = cell;
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public int gCost() {
        return gCost;
    }

    public int fCost() {
        return gCost + hCost;
    }

    public int hCost() {
        return hCost;
    }

    void setgCost(int gCost) {
        this.gCost = gCost;
    }

    void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public boolean isWalkable() {
        return cell.isWalkable();
    }

    public void setParent(AStartCell parent) {
        this.parent = parent;
    }

    public AStartCell getParent() {
        return parent;
    }

    public Cell getCell() {
        return cell;
    }


}
