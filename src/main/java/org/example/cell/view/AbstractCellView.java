package org.example.cell.view;

import javafx.scene.Node;
import javafx.scene.layout.Region;

import org.example.cell.AbstractCellModel;

public abstract class AbstractCellView<T extends Region, M extends AbstractCellModel> {
    protected double size;
    protected M cellModel;

    public AbstractCellView(double size, M cellModel) {
        this.size = size;
        this.cellModel = cellModel;

        getView().setPrefHeight(size);
        getView().setPrefWidth(size);
    }

    public void customizeStartCell() {
        getView().getStyleClass().add("default-start-cell-style");
    }

    public void customizeGoalCell() {
        getView().getStyleClass().add("default-goal-cell-style");
    }

    public void customizeNormalCell() {
        getView().getStyleClass().add("default-normal-cell-style");
    }

    public void customizeWallCell() {
        getView().getStyleClass().add("default-wall-cell-style");
    }

    public abstract T getView();
}
