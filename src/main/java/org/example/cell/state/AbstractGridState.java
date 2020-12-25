package org.example.cell.state;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import org.example.cell.AbstractCellModel;
import org.example.cell.Point;

public abstract class AbstractGridState<T extends AbstractCellModel> {
    protected IntegerProperty width = new SimpleIntegerProperty();
    protected IntegerProperty height = new SimpleIntegerProperty();

    private final ObjectProperty<Point> startingPoint = new SimpleObjectProperty<>();
    private final ObjectProperty<Point> goalPoint = new SimpleObjectProperty<>();

    public AbstractGridState(int width, int height) {
        setWidth(width);
        setHeight(height);
        setStartingPoint(Point.of(0, 0));
        setGoalPoint(Point.of(width - 1, height - 1));
    }

    public abstract T getCell(Point coordinate);

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint.set(startingPoint);
    }

    private void setGoalPoint(Point goalPoint) {
        this.goalPoint.set(goalPoint);
    }

    public ObjectProperty<Point> startingPointProperty() {
        return startingPoint;
    }

    public ObjectProperty<Point> goalPointProperty() {
        return goalPoint;
    }

    public void setWidth(int width) {
        this.width.setValue(width);
    }

    public void setHeight(int height) {
        this.height.setValue(height);
    }

    public int getWidth() {
        return width.get();
    }

    public int getHeight() {
        return height.get();
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public IntegerProperty heightProperty() {
        return height;
    }
}
