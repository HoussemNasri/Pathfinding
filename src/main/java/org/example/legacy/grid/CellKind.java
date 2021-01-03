package org.example.legacy.grid;

public enum CellKind {
    START_CELL, GOAL_CELL, NORMAL_CELL, WALL_CELL, OPEN_CELL, CLOSED_CELL, PATH_CELL;

    private final int[] params;

    CellKind(int... params) {
        this.params = params;
    }

    public int[] getParams() {
        return params;
    }
}
