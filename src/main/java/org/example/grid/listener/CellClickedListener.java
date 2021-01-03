package org.example.grid.listener;

import org.example.grid.listener.cellevent.CellClickedEvent;

public interface CellClickedListener extends GridListener {
    void onCellClicked(CellClickedEvent clickedEvent);
}
