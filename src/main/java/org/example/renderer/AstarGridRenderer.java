package org.example.renderer;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.example.cell.AstarCell;
import org.example.cell.Point;
import org.example.cell.state.AstarGridState;
import org.example.cell.view.AstarCellView;

public class AstarGridRenderer extends AbstractGridRenderer<AstarCell, AstarGridState, AstarCellView> {

    public AstarGridRenderer(AstarGridState gridState, double cellSize) {
        super(gridState, cellSize);
        render();
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
