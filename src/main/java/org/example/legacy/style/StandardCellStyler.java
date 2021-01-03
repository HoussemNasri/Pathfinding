package org.example.legacy.style;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

import org.example.legacy.grid.CellView;
import org.example.legacy.grid.CellKind;

/**
 * This is the default style for standard cell that can be wallCell or normalCell
 */
public class StandardCellStyler implements CellStyler {
    private static StandardCellStyler INSTANCE = null;

    @Override
    public void style(CellView cellView) {
        CellKind cellKind = cellView.getCellKind();
        switch (cellKind) {
            case NORMAL_CELL -> styleNormal(cellView);
            case WALL_CELL -> styleWall(cellView);
        }
    }

    private void styleNormal(CellView cellView) {
        cellView.setBackground(createBackground(Colors.NORMAL_CELL_FILL));
    }

    private void styleWall(CellView cellView) {
        cellView.setBackground(createBackground(Colors.WALL_CELL_FILL));
    }

    private Background createBackground(Paint color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, null, null);
        Background background = new Background(backgroundFill);
        return background;
    }

    @Override
    public Set<CellKind> getConcernedKinds() {
        Set<CellKind> set = new HashSet<>() {{
            add(CellKind.NORMAL_CELL);
            add(CellKind.WALL_CELL);
        }};

        return set;
    }

    public static StandardCellStyler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new StandardCellStyler();
        return INSTANCE;
    }
}
