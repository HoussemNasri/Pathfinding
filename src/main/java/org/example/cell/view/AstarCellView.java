package org.example.cell.view;

import javafx.beans.Observable;
import javafx.scene.layout.StackPane;

import org.example.cell.AstarCell;

public class AstarCellView extends AbstractCellView<StackPane, AstarCell> {

    public AstarCellView(double size, AstarCell cellModel) {
        super(size, cellModel);
        customizeCell(null, null, cellModel.getType());
        cellModel.cellTypeProperty().addListener(this::customizeCell);
    }

    private void customizeCell(Observable o, AstarCell.CellType ol, AstarCell.CellType cellType) {
        switch (cellType) {
            case START_CELL -> customizeStartCell();
            case GOAL_CELL -> customizeGoalCell();
            case NORMAL_CELL -> customizeNormalCell();
            case WALL_CELL -> customizeWallCell();

            case OPEN_CELL -> customizeOpenCell();
            case CLOSE_CELL -> customizeCloseCell();
            case PATH_CELL -> customizePathCell();
        }
    }

    private void customizeOpenCell() {
        getView().getStyleClass().add("astar-open-cell-style");
    }

    private void customizeCloseCell() {
        getView().getStyleClass().add("astar-close-cell-style");
    }

    private void customizePathCell() {
        getView().getStyleClass().add("astar-path-cell-style");
    }

    @Override
    public void initializeView() {
        view = new StackPane();
    }
}
