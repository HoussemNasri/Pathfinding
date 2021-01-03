package org.example.grid.listener.cellevent;

import javafx.scene.input.MouseButton;

import org.example.grid.cell.BaseCell;
import org.example.grid.state.AbstractGridState;

public class CellClickedEvent implements CellEvent {
    private final AbstractGridState<?> gridState;
    private final BaseCell clickedCell;
    private final MouseButton mouseButton;

    public CellClickedEvent(AbstractGridState<?> gridState, BaseCell clickedCell, MouseButton mouseButton) {
        this.gridState = gridState;
        this.clickedCell = clickedCell;
        this.mouseButton = mouseButton;
    }

    public AbstractGridState<?> getGridState() {
        return gridState;
    }

    public BaseCell getClickedCell() {
        return clickedCell;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public static CellClickedEvent of(AbstractGridState<?> gridState, BaseCell clickedCell, MouseButton mouseButton) {
        return new CellClickedEvent(gridState, clickedCell, mouseButton);
    }
}
