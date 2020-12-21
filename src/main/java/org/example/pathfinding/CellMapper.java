package org.example.pathfinding;

import org.example.grid.Cell;

public class CellMapper implements AStartMapper<AStartCell, Cell> {
    private static CellMapper INSTANCE = null;

    private CellMapper() {

    }

    @Override
    public Cell mapFrom(AStartCell aStartCell) {
        return null;
    }

    @Override
    public AStartCell mapTo(Cell cell) {
        return new AStartCell(cell, 0, 0);
    }

    public static CellMapper getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CellMapper();
        return INSTANCE;
    }
}
