package org.example.grid.view.cell;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class SimpleCellView extends Pane {
    public SimpleCellView(String styleClass) {
        getStyleClass().setAll(styleClass);
    }
}
