package org.example.toolbar;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class ToolbarView extends ToolBar {
    private final Toolbar toolbar;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final List<ToolbarListener> listeners = new ArrayList<>();

    public ToolbarView(Toolbar toolbar) {
        this.toolbar = toolbar;
        buildToolbar();
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            int indexOfSelectedToggle = toggleGroup.getToggles().indexOf(newValue);
            for (ToolbarListener listener : listeners) {
                listener.onToolSelected(toolbar.getTools().get(indexOfSelectedToggle));
            }
        });
    }

    private void buildToolbar() {
        addGridEditorTools();
        addSeparator();
    }

    private ToggleButton createToolView(Tool tool) {
        ToggleButton button = new ToggleButton();
        ImageView icon = new ImageView(tool.getIconUri());
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        button.setGraphic(icon);

        button.setTooltip(new Tooltip(tool.getDescription()));

        return button;
    }

    private void addSeparator() {
        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setPadding(new Insets(0, 10, 0, 10));
        getItems().add(separator);
    }

    private void addGridEditorTools() {
        for (Tool tool : toolbar.getTools()) {
            ToggleButton toolView = createToolView(tool);
            toolView.setToggleGroup(toggleGroup);
            getItems().add(toolView);
        }
    }

    public void registerListener(ToolbarListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(ToolbarListener listener) {
        listeners.remove(listener);
    }
}
