package org.example.grid.view.cell;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.example.algorithms.astar.AstarCost;

public class OpenCellView extends BorderPane {
    Text fCostTextView = new Text();

    private HBox gCostLayout = new HBox();
    private HBox hCostLayout = new HBox();
    private HBox fCostLayout = new HBox(fCostTextView);

    private AstarCost cost;

    public OpenCellView(AstarCost cost) {
        this.cost = cost;
        setupView();
        updateView();
    }

    private void updateView() {
        fCostTextView.setText(String.valueOf(cost.fCost()));
    }

    private void setupView() {
        getStyleClass().setAll("astar-open-cell-style");
        fCostTextView.setFont(Font.font(20));
        fCostTextView.setFill(Color.INDIGO);
        setCenter(fCostLayout);

        fCostLayout.setAlignment(Pos.CENTER);
    }

    public void setCost(AstarCost cost) {
        this.cost = cost;
        updateView();
    }
}
