package org.example.legacy.toolbar;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import org.example.utils.Icons;

public class Toolbar {
    private final List<Tool> tools = new ArrayList<>();
    private static Toolbar INSTANCE = null;

    private final IntegerProperty selectedToolIndex = new SimpleIntegerProperty();

    public Toolbar(List<Tool> tools) {
        this.tools.addAll(tools);
    }

    public static Toolbar getDefaultToolbar() {
        if (INSTANCE == null) {
            INSTANCE = new Toolbar(createTools());
        }
        return INSTANCE;
    }

    private static List<Tool> createTools() {
        Tool wallDrawer = new Tool("Wall Drawer", "Draw wall on the board", Icons.BRICK_WALL_ICON);
        Tool wallRemover = new Tool("Wall Remover", "Remove walls from the board", Icons.TROWEL_ICON);
        Tool gridClearer = new Tool("Clear Grid", "Clear the board", Icons.BROOM_ICON);
        Tool startPathfinding = new Tool("Start Pathfinding", "Start the pathfinding animation", Icons.PLAY_ICON);

        List<Tool> tools = new ArrayList<>();
        return tools;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public IntegerProperty selectedToolIndexProperty() {
        return selectedToolIndex;
    }
}
