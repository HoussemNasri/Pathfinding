package org.example.legacy.style;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

import org.example.legacy.grid.CellView;
import org.example.legacy.grid.CellKind;

public class StartGoalStyler implements CellStyler {
    private static StartGoalStyler INSTANCE = null;

    private StartGoalStyler() {

    }

    @Override
    public void style(CellView cellView) {
        CellKind cellKind = cellView.getCellKind();
        switch (cellKind) {
            case START_CELL -> startStyle(cellView);
            case GOAL_CELL -> goalStyle(cellView);
        }
    }

    private Background createBackground(Paint color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, null, null);
        Background background = new Background(backgroundFill);
        return background;
    }

    private void startStyle(CellView cellView) {
        cellView.setBackground(createBackground(Colors.START_CELL_FILL));
    }

    private void goalStyle(CellView cellView) {
        cellView.setBackground(createBackground(Colors.GOAL_CELL_FILL));
    }

    @Override
    public Set<CellKind> getConcernedKinds() {
        Set<CellKind> set = new HashSet<>() {{
            add(CellKind.START_CELL);
            add(CellKind.GOAL_CELL);
        }};

        return set;
    }

    public static StartGoalStyler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new StartGoalStyler();
        return INSTANCE;
    }
}
