package org.example.gridlistener;

import org.example.cell.AbstractCellModel;
import org.example.cell.state.AbstractGridState;

public interface CellClickedListener extends GridListener {
    void onCellClicked(AbstractGridState<?> gridState, AbstractCellModel cell);
}
