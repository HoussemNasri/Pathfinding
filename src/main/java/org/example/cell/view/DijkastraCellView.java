package org.example.cell.view;

import javafx.scene.layout.StackPane;

import org.example.cell.DijkstraCell;

public class DijkastraCellView extends AbstractCellView<StackPane, DijkstraCell> {

    public DijkastraCellView(double size, DijkstraCell cellModel) {
        super(size, cellModel);
    }

    @Override
    public StackPane getView() {
        return null;
    }
}
