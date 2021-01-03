package org.example.exception;

import javafx.geometry.Point2D;

import org.example.legacy.grid.Cell;

public class CoordinateOutOfBoundsException extends RuntimeException {
    public CoordinateOutOfBoundsException(int x, int y) {
        super(new Cell(x, y) + " Coordinate is out of bounds");
    }

    public CoordinateOutOfBoundsException(Point2D coordinate) {
        super(new Cell(coordinate) + " Coordinate is out of bounds");
    }
}
