package org.example.algorithms.aStar;

public class AstarCost {
    private int gCost;
    private int hCost;

    public AstarCost(int gCost, int hCost) {
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public AstarCost() {
        this(-1, -1);
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
}
