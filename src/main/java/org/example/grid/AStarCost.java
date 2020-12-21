package org.example.grid;

public class AStarCost {
    private int gCost;
    private int hCost;
    private int fCost;

    public AStarCost(int gCost, int hCost, int fCost) {
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = fCost;
    }

    public AStarCost() {
        this(-1, -1, -1);
    }

    public int gCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int hCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int fCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public boolean isAssigned() {
        return hCost != -1 && gCost != -1 && fCost != -1;
    }
}
