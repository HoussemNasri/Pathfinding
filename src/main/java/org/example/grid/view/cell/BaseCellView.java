package org.example.grid.view.cell;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import org.example.grid.cell.BaseCell;

public abstract class BaseCellView<M extends BaseCell> {
    protected double size;
    protected M cellModel;
    protected Pane view;

    public BaseCellView(double size, M cellModel) {
        this.size = size;
        this.cellModel = cellModel;
        view = new StackPane();

        getView().setPrefHeight(size);
        getView().setPrefWidth(size);
    }

    public void customizeStartCell() {
        this.view.getChildren().clear();
        _setStyle(getHomeCellStyleClass());
    }

    public void customizeGoalCell() {
        this.view.getChildren().clear();
        _setStyle(getDestinationCelLStyleClass());
    }

    public void customizeNormalCell() {
        /*this.view.getChildren().clear();
        _setStyle(getNormalCellStyleClass());*/
        setView(new SimpleCellView(getNormalCellStyleClass()));
    }

    public void customizeWallCell() {
        System.out.println("wall");
        /*this.view.getChildren().clear();
        _setStyle(getWallCellStleClass());*/
        setView(new SimpleCellView(getWallStyleClass()));
    }

    public void _setStyle(String styleClass) {
        getView().getStyleClass().setAll(styleClass);
    }

    protected String getHomeCellStyleClass() {
        return "default-start-cell-style";
    }

    protected String getDestinationCelLStyleClass() {
        return "default-goal-cell-style";
    }

    protected String getNormalCellStyleClass() {
        return "default-normal-cell-style";
    }

    protected String getWallStyleClass() {
        return "default-wall-cell-style";
    }

    public Pane getView() {
        return view;
    }

    public void setView(Pane stackPane) {
        getView().getChildren().clear();
        getView().getChildren().add(stackPane);

        GridPane.setColumnIndex(stackPane, cellModel.getCoordinate().getX());
        GridPane.setColumnIndex(stackPane, cellModel.getCoordinate().getY());
    }
}
