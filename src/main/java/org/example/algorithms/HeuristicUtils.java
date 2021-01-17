package org.example.algorithms;

import org.example.grid.cell.BaseCell;

public class HeuristicUtils {
    /**
     * Compute distance between cell and destinationCell
     */
    public static int manhattanDistance(BaseCell cell, BaseCell destinationCell) {
        int dx = Math.abs(cell.getCoordinate().getX() - destinationCell.getCoordinate().getX());
        int dy = Math.abs(cell.getCoordinate().getY() - destinationCell.getCoordinate().getY());

        int nonDiagonalDistance = 10;

        return nonDiagonalDistance * (dx + dy);
    }

    public static int diagonalDistance(BaseCell cell, BaseCell destinationCell) {
        int dx = Math.abs(cell.getCoordinate().getX() - destinationCell.getCoordinate().getX());
        int dy = Math.abs(cell.getCoordinate().getY() - destinationCell.getCoordinate().getY());

        int nonDiagonalDistance = 10;
        int diagonalDistance = 14;

        return nonDiagonalDistance * (dx + dy) + (diagonalDistance - 2 * diagonalDistance) * Math.max(dx, dy);
    }
}
