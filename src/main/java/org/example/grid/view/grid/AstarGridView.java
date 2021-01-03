package org.example.grid.view.grid;

import javafx.scene.layout.GridPane;

import org.example.grid.cell.AstarCell;
import org.example.grid.Point;
import org.example.grid.state.PathfindingGridState;
import org.example.grid.view.cell.AstarCellView;

public class AstarGridView extends BaseGridView<AstarCell, AstarCellView> {

    public AstarGridView(PathfindingGridState<AstarCell> gridState, double cellSize) {
        super(gridState, cellSize);
    }

    @Override
    protected AstarCellView createCellView(Point cellCoordinate) {
        return new AstarCellView(cellSize, gridState.getCell(cellCoordinate));
    }

    @Override
    public GridPane getView() {
        return gridView;
    }
}
