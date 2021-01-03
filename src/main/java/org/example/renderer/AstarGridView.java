package org.example.renderer;

import javafx.scene.layout.GridPane;

import org.example.cell.AstarCell;
import org.example.cell.Point;
import org.example.cell.state.PathfindingGridState;
import org.example.cell.view.AstarCellView;

public class AstarGridView extends PathfindingGridView<AstarCell, PathfindingGridState<AstarCell>, AstarCellView> {

    public AstarGridView(PathfindingGridState gridState, double cellSize) {
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
