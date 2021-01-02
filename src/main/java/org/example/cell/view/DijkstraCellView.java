package org.example.cell.view;

import javafx.scene.layout.StackPane;

import org.example.cell.DijkstraCell;

public class DijkstraCellView extends AbstractCellView<DijkstraCell> {
    private StackPane view;

    public DijkstraCellView(double size, DijkstraCell cellModel) {
        super(size, cellModel);
    }

}
