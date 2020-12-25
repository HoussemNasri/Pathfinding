package org.example.cell.view;

import javafx.scene.layout.StackPane;

import org.example.cell.DijkstraCell;

public class DijkstraCellView extends AbstractCellView<StackPane, DijkstraCell> {

    public DijkstraCellView(double size, DijkstraCell cellModel) {
        super(size, cellModel);
    }


    @Override
    public void initializeView() {
        view = new StackPane();
    }
}
