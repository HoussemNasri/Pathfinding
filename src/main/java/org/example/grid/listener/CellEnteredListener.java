package org.example.grid.listener;

import org.example.grid.cell.BaseCell;

public interface CellEnteredListener extends GridListener {
    void onCellEntered(BaseCell cameFromCell, BaseCell toCell);
}
