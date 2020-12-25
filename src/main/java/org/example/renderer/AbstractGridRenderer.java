package org.example.renderer;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.example.cell.AbstractCellModel;
import org.example.cell.Point;
import org.example.cell.state.AbstractGridState;
import org.example.cell.view.AbstractCellView;

/**
 * @param <C> The cell model
 * @param <S> The grid state
 * @param <V> The cell view
 * */
public abstract class AbstractGridRenderer<C extends AbstractCellModel, S extends AbstractGridState<C>, V extends AbstractCellView<? extends Region, C>> implements GridRenderer<GridPane> {
    private static final double BORDER_WIDTH = 1;
    private static final Paint BORDER_PANT = Color.BLACK;

    protected int width;
    protected int height;
    protected double cellSize;
    protected S gridState;

    protected GridPane gridView;

    public AbstractGridRenderer(S gridState, double cellSize) {
        this.gridState = gridState;
        this.cellSize = cellSize;
        this.width = gridState.getWidth();
        this.height = gridState.getHeight();
        this.gridView = new GridPane();
        styleGridView();
    }

    private void styleGridView() {
        // turn layout pixel snapping off on the grid so that grid lines will be an even width.
        gridView.setSnapToPixel(false);
        gridView.setBorder(createBorder());
        // style the grid so that it has a background and gaps around the grid and between the
        // grid cells so that the background will show through as grid lines.
        gridView.setBackground(createBackground());
        gridView.setHgap(BORDER_WIDTH);
        gridView.setVgap(BORDER_WIDTH);
    }

    private Border createBorder() {
        BorderWidths borderWidths = new BorderWidths(BORDER_WIDTH);
        BorderStroke borderStroke = new BorderStroke(null, null, null, borderWidths);
        Border border = new Border(borderStroke);

        return border;
    }

    private Background createBackground() {
        BackgroundFill grisViewFill = new BackgroundFill(BORDER_PANT, CornerRadii.EMPTY, Insets.EMPTY);
        Background gridViewBackground = new Background(grisViewFill);

        return gridViewBackground;
    }

    protected abstract V createCellView(Point coordinate);

    @Override
    public void render() {
        gridView.setMinSize(cellSize * width, cellSize * height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                AbstractCellView<?, C> cellView = createCellView(Point.of(x, y));
                GridPane.setColumnIndex(cellView.getView(), x);
                GridPane.setRowIndex(cellView.getView(), y);
                gridView.add(cellView.getView(), x, y);
            }
        }
    }

    public S getGridState() {
        return gridState;
    }
}
