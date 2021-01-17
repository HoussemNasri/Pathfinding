package org.example.algorithms.astar;

import org.example.grid.cell.AstarCell;
import org.jetbrains.annotations.NotNull;

public class AstarCost implements Cloneable, Comparable<AstarCost> {
    private int gCost;
    private int hCost;

    public AstarCost(int gCost, int hCost) {
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public AstarCost() {
        this(0, 0);
    }

    public int gCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int hCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public int fCost() {
        return hCost + gCost;
    }

    public boolean isComplete() {
        return hCost != -1 && gCost != -1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(@NotNull AstarCost o) {
        AstarCost thisCost = this;
        if (thisCost.fCost() > o.fCost()) {
            return 1;
        } else {
            if (this.fCost() == o.fCost() && thisCost.hCost() > o.hCost()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
