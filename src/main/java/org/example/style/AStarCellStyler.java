package org.example.style;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.scene.paint.Paint;

import org.example.grid.CellView;
import org.example.grid.CellKind;

public class AStarCellStyler implements CellStyler {
    private static AStarCellStyler INSTANCE = null;

    @Override
    public void style(CellView cellView) {
        CellKind cellKind = cellView.getCellKind();

        switch (cellKind) {
            case OPEN_CELL -> openStyle(cellView);
            case CLOSED_CELL -> closeStyle(cellView);
            case PATH_CELL -> pathStyle(cellView);
        }
    }

    private Background createBackground(Paint color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, null, null);
        Background background = new Background(backgroundFill);
        return background;
    }

    private void openStyle(CellView cellView) {
        cellView.setBackground(createBackground(Colors.OPEN_CELL_FILL));
    }

    private void closeStyle(CellView cellView) {
        cellView.setBackground(createBackground(Colors.CLOSED_CELL_FILL));
    }

    private void pathStyle(CellView cellView) {
        cellView.setBackground(createBackground(Colors.PATH_CELL_FILL));
    }

    @Override
    public Set<CellKind> getConcernedKinds() {
        Set<CellKind> set = new HashSet<>() {{
            add(CellKind.OPEN_CELL);
            add(CellKind.CLOSED_CELL);
            add(CellKind.PATH_CELL);
        }};

        return set;
    }

    public static AStarCellStyler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AStarCellStyler();
        return INSTANCE;
    }
}
