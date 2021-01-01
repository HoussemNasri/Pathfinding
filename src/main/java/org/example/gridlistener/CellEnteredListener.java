package org.example.gridlistener;

import org.example.cell.AbstractCellModel;

public interface CellEnteredListener extends GridListener {
    void onCellEntered(AbstractCellModel cameFromCell, AbstractCellModel toCell);
}
