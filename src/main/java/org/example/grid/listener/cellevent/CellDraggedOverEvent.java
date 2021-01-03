package org.example.grid.listener.cellevent;

import javafx.scene.input.MouseButton;

import org.example.grid.cell.BaseCell;
import org.example.grid.state.AbstractGridState;

public class CellDraggedOverEvent implements CellEvent {
    private final AbstractGridState<?> gridState;
    private final BaseCell draggedOverCell;
    private final MouseButton mouseButton;

    public CellDraggedOverEvent(AbstractGridState<?> gridState, BaseCell draggedOverCell, MouseButton mouseButton) {
        this.gridState = gridState;
        this.draggedOverCell = draggedOverCell;
        this.mouseButton = mouseButton;
    }

    public AbstractGridState<?> getGridState() {
        return gridState;
    }

    public BaseCell getDraggedOverCell() {
        return draggedOverCell;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public static CellDraggedOverEvent of(AbstractGridState<?> gridState, BaseCell clickedCell, MouseButton mouseButton) {
        return new CellDraggedOverEvent(gridState, clickedCell, mouseButton);
    }
}
