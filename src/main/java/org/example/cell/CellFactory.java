package org.example.cell;

public class CellFactory<T extends AbstractCellModel> {
    public T createCell(Class<T> clazz, Point coordinate) {
        if (clazz.equals(AstarCell.class)) {
            return (T) new AstarCell(coordinate);
        } else if (clazz.equals(DijkstraCell.class)) {
            return (T) new DijkstraCell(coordinate, null);
        }
        return null;
    }

}
