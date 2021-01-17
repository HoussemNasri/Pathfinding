package org.example.grid.listener.cellevent;

import javafx.scene.input.MouseButton;

import org.example.grid.cell.BaseCell;
import org.example.grid.state.BaseGridState;

public class CellDraggedOverEvent implements CellEvent {
    private final BaseGridState<?> gridState;
    private final BaseCell draggedOverCell;
    private final MouseButton mouseButton;

    public CellDraggedOverEvent(BaseGridState<?> gridState, BaseCell draggedOverCell, MouseButton mouseButton) {
        this.gridState = gridState;
        this.draggedOverCell = draggedOverCell;
        this.mouseButton = mouseButton;
    }

    public BaseGridState<?> getGridState() {
        return gridState;
    }

    public BaseCell getDraggedOverCell() {
        return draggedOverCell;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public static CellDraggedOverEvent of(BaseGridState<?> gridState, BaseCell clickedCell, MouseButton mouseButton) {
        return new CellDraggedOverEvent(gridState, clickedCell, mouseButton);
    }
}
