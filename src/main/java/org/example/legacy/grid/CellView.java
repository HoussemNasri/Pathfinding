package org.example.legacy.grid;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import org.example.algorithms.astar.AstarCost;
import org.example.legacy.style.CellStyler;
import org.example.legacy.style.StandardCellStyler;
import org.example.legacy.style.StartGoalStyler;

public class CellView extends StackPane {
    private Cell cellModel;
    private double size;
    private List<CellStyler> stylers = new ArrayList<>();

    private Text gCostTextView;
    private Text hCostTextView;
    private Text fCostTextView;

    public CellView(Cell cellModel, double size) {
        this.cellModel = cellModel;
        this.size = size;

        setPrefWidth(size);
        setPrefHeight(size);
        initStyle();

        cellModel.costProperty().addListener(this::updateAStar);
        cellModel.cellKindProperty().addListener(this::handleKindChanged);
    }

    private void updateAStar(ObservableValue<? extends AstarCost> observable, AstarCost o, AstarCost cost) {
        if (!cost.isComplete() || getPrefHeight() < 40)
            return;
        gCostTextView = createRegularTextView(String.valueOf(cost.gCost()));
        hCostTextView = createRegularTextView(String.valueOf(cost.hCost()));
        fCostTextView = createBoldTextView(String.valueOf(cost.fCost()));

        HBox gCostWrapper = new HBox();
        gCostWrapper.setAlignment(Pos.TOP_LEFT);
        gCostWrapper.setPadding(new Insets(computePadding(), 0, 0, computePadding()));
        gCostWrapper.getChildren().add(gCostTextView);

        HBox hCostWrapper = new HBox();
        hCostWrapper.setAlignment(Pos.TOP_RIGHT);
        hCostWrapper.setPadding(new Insets(computePadding(), computePadding(), 0, 0));
        hCostWrapper.getChildren().add(hCostTextView);

        HBox fCostWrapper = new HBox();
        fCostWrapper.setAlignment(Pos.CENTER);
        fCostWrapper.setPadding(new Insets(20, 0, 0, 0));
        fCostWrapper.getChildren().add(fCostTextView);

        getChildren().add(gCostWrapper);
        getChildren().add(hCostWrapper);
        getChildren().add(fCostWrapper);
    }

    private Text createBoldTextView(String txt) {
        Text text = new Text(txt);
        Font font = Font.font("Trebuchet MS", FontWeight.BOLD, computeBigFontSize());
        text.setFont(font);
        text.setFill(Color.BLACK);

        return text;
    }

    private Text createRegularTextView(String txt) {
        Text text = new Text(txt);
        Font font = Font.font("Trebuchet MS", FontWeight.NORMAL, computerSmallFontSize());
        text.setFont(font);
        text.setFill(Color.BLACK);

        return text;
    }

    private int computerSmallFontSize() {
        return (int) ((getPrefHeight() / 75d) * 16);
    }

    private int computeBigFontSize() {
        System.out.println(getPrefHeight());
        return (int) ((getPrefHeight() / 75d) * 20);
    }

    private double computePadding() {
        return (getPrefHeight() / 75d) * 5;
    }

    private void handleKindChanged(ObservableValue<? extends CellKind> observable, CellKind oldValue, CellKind newValue) {
        for (CellStyler styler : stylers) {
            if (styler.getConcernedKinds().contains(newValue))
                styler.style(CellView.this);
        }
    }

    private void initStyle() {
        StandardCellStyler.getInstance().style(this);
        StartGoalStyler.getInstance().style(this);
    }

    public void forceStyler(CellStyler cellStyle) {
        cellStyle.style(this);
    }

    public Cell getCellModel() {
        return cellModel;
    }

    public double getSize() {
        return size;
    }

    public void setKind(CellKind cellKind) {
        getCellModel().cellKindProperty().setValue(cellKind);
    }

    public CellKind getCellKind() {
        return getCellModel().cellKindProperty().get();
    }

    public void registerStyler(CellStyler cellStyler) {
        stylers.add(cellStyler);
    }
}
