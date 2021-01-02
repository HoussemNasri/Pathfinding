package org.example.gridlistener.cellevent;

import javafx.scene.input.MouseButton;

import org.example.cell.AbstractCellModel;
import org.example.cell.state.AbstractGridState;

public class CellDraggedOverEvent implements CellEvent {
    private final AbstractGridState<?> gridState;
    private final AbstractCellModel draggedOverCell;
    private final MouseButton mouseButton;

    public CellDraggedOverEvent(AbstractGridState<?> gridState, AbstractCellModel draggedOverCell, MouseButton mouseButton) {
        this.gridState = gridState;
        this.draggedOverCell = draggedOverCell;
        this.mouseButton = mouseButton;
    }

    public AbstractGridState<?> getGridState() {
        return gridState;
    }

    public AbstractCellModel getDraggedOverCell() {
        return draggedOverCell;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public static CellDraggedOverEvent of(AbstractGridState<?> gridState, AbstractCellModel clickedCell, MouseButton mouseButton) {
        return new CellDraggedOverEvent(gridState, clickedCell, mouseButton);
    }
}
