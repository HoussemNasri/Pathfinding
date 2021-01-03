package org.example.grid.view.cell;

import javafx.scene.layout.StackPane;

import org.example.grid.cell.DijkstraCell;

public class DijkstraCellView extends BaseCellView<DijkstraCell> {
    private StackPane view;

    public DijkstraCellView(double size, DijkstraCell cellModel) {
        super(size, cellModel);
    }

}
