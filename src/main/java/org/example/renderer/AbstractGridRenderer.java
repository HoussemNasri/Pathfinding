package org.example.renderer;

import javafx.scene.layout.Region;

import org.example.cell.AbstractCellModel;
import org.example.cell.state.AbstractGridState;
import org.example.cell.view.AbstractCellView;


/**
 * @param <C> The cell model
 * @param <S> The grid state
 * @param <V> The cell view
 * @param <ViewType> The view of the grid
 * */
public abstract class AbstractGridRenderer<C extends AbstractCellModel, S extends AbstractGridState<C>, V extends AbstractCellView<? extends Region, C>, ViewType extends Region> implements GridRenderer<ViewType>{
    protected int width;
    protected int height;
    protected double cellSize;
    protected S gridState;

    public AbstractGridRenderer(S gridState, double cellSize) {
        this.gridState = gridState;
        this.cellSize = cellSize;
        this.width = gridState.getWidth();
        this.height = gridState.getHeight();
    }

    public S getGridState() {
        return gridState;
    }
}
