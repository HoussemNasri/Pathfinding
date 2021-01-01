package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import de.saxsys.mvvmfx.ViewModel;
import org.example.cell.AbstractCellModel;
import org.example.cell.CellType;
import org.example.cell.state.AbstractGridState;
import org.example.gridlistener.CellClickedListener;

import static org.example.MyToolbarView.*;

public class MyToolbarViewModel implements ViewModel, CellClickedListener {
    private final IntegerProperty selectedTool = new SimpleIntegerProperty(0);

    public MyToolbarViewModel() {
        handleToolSelection();
    }

    public void handleToolSelection() {
        selectedTool.addListener((observable, oldValue, newValue) -> {
            System.out.println(MyToolbarViewModel.this);
        });
    }

    public IntegerProperty selectedToolProperty() {
        System.out.println(selectedTool);
        return selectedTool;
    }

    public int getSelectedTool() {
        return selectedTool.get();
    }

    @Override
    public void onCellClicked(AbstractGridState<?> gridState, AbstractCellModel cell) {
        if (getSelectedTool() == HOME_MARKER_TOOL) {
            if (cell.getType() != CellType.NORMAL_CELL)
                return;
            gridState.setStartingPoint(cell.getCoordinate());
        } else if (getSelectedTool() == DESTINATION_MARKER_TOOL) {
            if (cell.getType() != CellType.NORMAL_CELL)
                return;
            gridState.setDestinationPoint(cell.getCoordinate());
        }
    }
}
