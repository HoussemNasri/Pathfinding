package org.example.gridlistener;

import org.example.cell.AbstractCellModel;
import org.example.cell.state.AbstractGridState;
import org.example.gridlistener.cellevent.CellDraggedOverEvent;

public interface CellDraggedOverListener {
    void onCellDraggedOver(CellDraggedOverEvent cellDraggedOverEvent);
}
