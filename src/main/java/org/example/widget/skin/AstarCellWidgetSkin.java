package org.example.widget.skin;

import org.example.widget.CellWidget;

public class AstarCellWidgetSkin extends CellWidgetSkin {
    public AstarCellWidgetSkin(CellWidget control) {
        super(control);
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }
}
