package org.example.grid;

import java.util.ArrayList;
import java.util.List;

import org.example.grid.cell.CellType;

public abstract class CellHistoryBrowser {
    private final List<CellType> cellHistory = new ArrayList<>();
    private int pointer = 0;

    public void stepIn() {
        if (canStepIn()) {
            pointer++;
        }
    }

    public void stepOut() {
        if (canStepOut()) {
            pointer--;
        }
    }

    public CellType peek() {
        if (cellHistory.size() < 1)
            return null;
        return cellHistory.get(pointer);
    }

    protected void addHistoryRecord(CellType historyRecord) {
        cellHistory.add(historyRecord);
    }

    public boolean canStepIn() {
        return pointer + 1 < cellHistory.size();
    }

    public boolean canStepOut() {
        return pointer - 1 >= 0;
    }
}
