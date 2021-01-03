package org.example.legacy.grid;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
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

import org.example.grid.GridRenderer;
import org.example.legacy.style.CellStyler;
import org.example.legacy.style.StandardCellStyler;
import org.example.utils.CoordinateUtils;

public class GridView implements GridRenderer<GridPane> {
    public static final double BORDER_WIDTH = 1d;
    public static final Paint BORDER_PANT = Color.valueOf("#2F3134");

    private GridState gridState;
    private double cellSize;
    private CellStyler cellStyler;

    private GridPane gridView;

    public GridView(GridState gridState, double cellSize, CellStyler cellStyler) {
        this.gridState = gridState;
        this.cellSize = cellSize;
        this.cellStyler = cellStyler;

        this.gridView = new GridPane();
        styleGridView();
        setupListeners();
        render();
    }

    public GridView(GridState gridState, double cellSize) {
        this(gridState, cellSize, new StandardCellStyler());
    }

    public GridView(GridState gridState) {
        this(gridState, 50d);
    }

    private void setupListeners() {
        gridView.setOnMouseDragged(this::handleDragEvent);
    }

    private void styleGridView() {
        // turn layout pixel snapping off on the grid so that grid lines will be an even width.
        gridView.setSnapToPixel(false);
        gridView.setBorder(createBorder());
        // style the grid so that it has a background and gaps around the grid and between the
        // grid cells so that the background will show through as grid lines.
        gridView.setBackground(createBackground());
        gridView.setHgap(computeHgap());
        gridView.setVgap(computeVgap());
    }

    @org.jetbrains.annotations.NotNull
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

    private double computeHgap() {
        return BORDER_WIDTH;
    }

    private double computeVgap() {
        return BORDER_WIDTH;
    }

    private CellView createCellView(Point2D thisCellCoordinate) {
        return new CellView(gridState.getCell(thisCellCoordinate), cellSize);
    }

    @Override
    public void render() {
        gridView.setMinSize(cellSize * gridState.getWidth(), cellSize * gridState.getHeight());
        for (int x = 0; x < gridState.getWidth(); x++) {
            for (int y = 0; y < gridState.getHeight(); y++) {
                Point2D thisCellCoordinate = new Point2D(x, y);

                CellView cellView = createCellView(thisCellCoordinate);

                GridPane.setColumnIndex(cellView, x);
                GridPane.setRowIndex(cellView, y);
                gridView.add(cellView, x, y);
            }
        }
    }

    public void registerStyler(CellStyler styler) {
        for (int i = 0; i < gridView.getChildren().size(); i++) {
            CellView cellView = (CellView) gridView.getChildren().get(i);
            cellView.registerStyler(styler);
        }
    }

    private void handleDragEvent(MouseEvent e) {
        Node node = e.getPickResult().getIntersectedNode();
        if (node == null)
            return;
        Integer x = GridPane.getColumnIndex(node);
        Integer y = GridPane.getRowIndex(node);
        if (x == null || y == null)
            return;
        Point2D cellCoordinate = new Point2D(x, y);
        if (!CoordinateUtils.isCoordinateCorrect(x, y, gridState.getWidth(), gridState.getHeight())
                || gridState.isStartCell(cellCoordinate)
                || gridState.isGoalCell(cellCoordinate)
                || !gridState.getCell(cellCoordinate).isWalkable())
            return;

        CellView cellView = getCellView(x, y);
        cellView.setKind(CellKind.WALL_CELL);
    }

    private CellView getCellView(int x, int y) {
        return (CellView)
                gridView.getChildren().get(x * getGridState().getHeight() + y);
    }

    @Override
    public GridPane getView() {
        return gridView;
    }

    public GridState getGridState() {
        return gridState;
    }
}
