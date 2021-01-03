package org.example.grid.view.grid;

import javafx.scene.layout.GridPane;

import org.example.grid.cell.DijkstraCell;
import org.example.grid.Point;
import org.example.grid.state.PathfindingGridState;
import org.example.grid.view.cell.DijkstraCellView;

public class DijkstraGridView extends BaseGridView<DijkstraCell, DijkstraCellView> {

    public DijkstraGridView(PathfindingGridState<DijkstraCell> gridState, double cellSize) {
        super(gridState, cellSize);
    }

    @Override
    public GridPane getView() {
        return null;
    }

    @Override
    protected DijkstraCellView createCellView(Point coordinate) {
        return null;
    }
}
