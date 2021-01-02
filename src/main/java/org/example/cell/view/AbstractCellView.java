package org.example.cell.view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import org.example.cell.AbstractCellModel;

public abstract class AbstractCellView<M extends AbstractCellModel> {
    protected double size;
    protected M cellModel;
    protected StackPane view;

    public AbstractCellView(double size, M cellModel) {
        this.size = size;
        this.cellModel = cellModel;
        view = new StackPane();

        getView().setPrefHeight(size);
        getView().setPrefWidth(size);
    }

    public void customizeStartCell() {
        _setStyle(getHomeCellStyleClass());
    }

    public void customizeGoalCell() {
        _setStyle(getDestinationCelLStyleClass());
    }

    public void customizeNormalCell() {
        _setStyle(getNormalCellStyleClass());
    }

    public void customizeWallCell() {
        _setStyle(getWallCellStleClass());
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

    protected String getWallCellStleClass() {
        return "default-wall-cell-style";
    }

    public StackPane getView(){
        return view;
    }

}
