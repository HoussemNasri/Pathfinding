package org.example.gridlistener.cellevent;

import javafx.scene.input.MouseButton;

import org.example.cell.AbstractCellModel;
import org.example.cell.state.AbstractGridState;

public class CellClickedEvent implements CellEvent {
    private final AbstractGridState<?> gridState;
    private final AbstractCellModel clickedCell;
    private final MouseButton mouseButton;

    public CellClickedEvent(AbstractGridState<?> gridState, AbstractCellModel clickedCell, MouseButton mouseButton) {
        this.gridState = gridState;
        this.clickedCell = clickedCell;
        this.mouseButton = mouseButton;
    }

    public AbstractGridState<?> getGridState() {
        return gridState;
    }

    public AbstractCellModel getClickedCell() {
        return clickedCell;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public static CellClickedEvent of(AbstractGridState<?> gridState, AbstractCellModel clickedCell, MouseButton mouseButton) {
        return new CellClickedEvent(gridState, clickedCell, mouseButton);
    }
}
