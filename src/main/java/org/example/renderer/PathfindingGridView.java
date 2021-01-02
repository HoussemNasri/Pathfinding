package org.example.renderer;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.example.cell.AbstractCellModel;
import org.example.cell.Point;
import org.example.cell.state.AbstractGridState;
import org.example.cell.view.AbstractCellView;
import org.example.gridlistener.CellClickedListener;
import org.example.gridlistener.CellDraggedOverListener;
import org.example.gridlistener.CellEnteredListener;
import org.example.gridlistener.cellevent.CellClickedEvent;
import org.example.gridlistener.cellevent.CellDraggedOverEvent;

/**
 * @param <C> The cell model
 * @param <S> The grid state
 * @param <V> The cell view
 */
public abstract class PathfindingGridView<C extends AbstractCellModel, S extends AbstractGridState<C>, V extends AbstractCellView<C>> implements GridRenderer<GridPane> {
    private static final double BORDER_WIDTH = 1;
    private static final Paint BORDER_PANT = Color.BLACK;

    protected int width;
    protected int height;
    protected double cellSize;
    protected S gridState;

    protected GridPane gridView;

    protected List<CellEnteredListener> cellEnteredListeners = new ArrayList<>();
    protected List<CellClickedListener> cellClickedListeners = new ArrayList<>();
    protected List<CellDraggedOverListener> cellDraggedOverListeners = new ArrayList<>();

    private AbstractCellModel previousEnteredCell = null;

    public PathfindingGridView(S gridState, double cellSize) {
        this.gridState = gridState;
        this.cellSize = cellSize;
        this.width = gridState.getWidth();
        this.height = gridState.getHeight();
        this.gridView = new GridPane();
        styleGridView();
        render();

        registerWidthListener();
        registerHeightListener();

        handleOnMouseClicked();
        handleOnMouseEntered();
        handleOnMouseDragged();
    }

    private void handleOnMouseClicked() {
        gridView.setOnMouseClicked(e -> {
            AbstractCellModel clickedCell = extractEventCell(e);
            if (clickedCell == null)
                return;
            cellClickedListeners.forEach(lis -> lis.onCellClicked(CellClickedEvent.of(gridState, clickedCell, e.getButton())));
        });
    }

    private void handleOnMouseEntered() {
        gridView.setOnMouseMoved(e -> {
            AbstractCellModel enteredCell = extractEventCell(e);
            if (enteredCell == previousEnteredCell)
                return;
            cellEnteredListeners.forEach(lis -> lis.onCellEntered(previousEnteredCell, enteredCell));
            previousEnteredCell = enteredCell;
        });
    }

    private void handleOnMouseDragged() {
        gridView.setOnMouseDragged(e -> {
            AbstractCellModel draggedOverCell = extractEventCell(e);
            if (draggedOverCell == null)
                return;
            cellDraggedOverListeners.forEach(lis -> lis.onCellDraggedOver(CellDraggedOverEvent.of(
                    gridState, draggedOverCell, e.getButton())));
        });
    }

    private AbstractCellModel extractEventCell(MouseEvent e) {
        Node node = e.getPickResult().getIntersectedNode();
        if (node == null)
            return null;
        Integer x = GridPane.getColumnIndex(node);
        Integer y = GridPane.getRowIndex(node);
        if (x == null || y == null)
            return null;
        return gridState.getCell(Point.of(x, y));
    }

    public void setCellSize(double cellSize) {
        this.cellSize = cellSize;
        render();
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
        gridView.setMaxHeight(cellSize * height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                AbstractCellView<C> cellView = createCellView(Point.of(x, y));
                GridPane.setColumnIndex(cellView.getView(), x);
                GridPane.setRowIndex(cellView.getView(), y);
                gridView.add(cellView.getView(), x, y);
            }
        }
    }

    private void registerWidthListener() {
        gridState.widthProperty().addListener((observable, oldValue, newValue) -> {
            PathfindingGridView.this.width = newValue.intValue();
            render();
        });
    }

    private void registerHeightListener() {
        gridState.widthProperty().addListener((observable, oldValue, newValue) -> {
            PathfindingGridView.this.width = newValue.intValue();
            render();
        });
    }

    public void registerCellEnteredListener(CellEnteredListener listener) {
        cellEnteredListeners.add(listener);
    }

    public void registerCellClickedListener(CellClickedListener listener) {
        cellClickedListeners.add(listener);
    }

    public void registerCellDraggedOverListener(CellDraggedOverListener listener) {
        cellDraggedOverListeners.add(listener);
    }

    public S getGridState() {
        return gridState;
    }
}
