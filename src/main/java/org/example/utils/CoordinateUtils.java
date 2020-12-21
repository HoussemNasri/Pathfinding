package org.example.utils;

import javafx.geometry.Point2D;

public class CoordinateUtils {

    public static boolean isCoordinateCorrect(Point2D coordinate, int gridWidth, int gridHeight) {
        return coordinate.getX() >= 0 && coordinate.getX() < gridWidth
                && coordinate.getY() >= 0 && coordinate.getY() < gridHeight;
    }

    public static boolean isCoordinateCorrect(int x, int y, int gridWidth, int gridHeight) {
        return isCoordinateCorrect(new Point2D(x, y), gridWidth, gridHeight);
    }
}
