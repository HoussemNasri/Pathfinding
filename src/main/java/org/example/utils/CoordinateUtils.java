package org.example.utils;

import javafx.geometry.Point2D;

import org.example.grid.Point;

public class CoordinateUtils {

    public static boolean inBounds(int x, int y, int gridWidth, int gridHeight) {
        return inBounds(new Point2D(x, y), gridWidth, gridHeight);
    }

    public static boolean notInBounds(int x, int y, int gridWidth, int gridHeight) {
        return !inBounds(x, y, gridWidth, gridHeight);
    }

    public static boolean inBounds(Point2D coordinate, int gridWidth, int gridHeight) {
        return coordinate.getX() >= 0 && coordinate.getX() < gridWidth
                && coordinate.getY() >= 0 && coordinate.getY() < gridHeight;
    }

    public static boolean notInBounds(Point2D coordinate, int gridWidth, int gridHeight) {
        return !inBounds(coordinate, gridWidth, gridHeight);
    }

    public static boolean inBounds(Point coordinate, int gridWidth, int gridHeight) {
        return coordinate.getX() >= 0 && coordinate.getX() < gridWidth
                && coordinate.getY() >= 0 && coordinate.getY() < gridHeight;
    }

    public static boolean notInBounds(Point coordinate, int gridWidth, int gridHeight) {
        return !inBounds(coordinate, gridWidth, gridHeight);
    }
}
