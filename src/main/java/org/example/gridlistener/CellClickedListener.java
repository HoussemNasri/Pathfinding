package org.example.gridlistener;

import org.example.cell.AbstractCellModel;
import org.example.cell.state.AbstractGridState;
import org.example.gridlistener.cellevent.CellClickedEvent;

public interface CellClickedListener extends GridListener {
    void onCellClicked(CellClickedEvent clickedEvent);
}
