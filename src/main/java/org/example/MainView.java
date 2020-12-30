package org.example;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import de.saxsys.mvvmfx.FluentViewLoader;
import org.example.cell.state.AstarGridState;
import org.example.renderer.AstarGridView;
import org.example.renderer.PathfindingGridView;
import org.example.toolbar.Toolbar;
import org.example.toolbar.ToolbarView;

public class MainView extends HBox {
    private ToolBar sideToolbar;
    private GridPane gridView;
    private PathfindingGridView<?, ?, ?> pathfindingGridView;

    public MainView() {
        setPathfindingGridView(createPathfindingGridView());
        setSideToolbar(createToolbarView());
        initLayout();
    }

    private void initLayout() {
        getChildren().add(sideToolbar);
        getChildren().add(gridView);
    }

    public void setPathfindingGridView(PathfindingGridView<?, ?, ?> pathfindingGridView) {
        this.pathfindingGridView = pathfindingGridView;
        gridView = pathfindingGridView.getView();
    }

    private ToolBar createToolbarView() {
        return (ToolBar) FluentViewLoader.fxmlView(MyToolbarView.class).load().getView();
        //return new ToolbarView(Toolbar.getDefaultToolbar());
    }

    private PathfindingGridView<?, ?, ?> createPathfindingGridView() {
        AstarGridState astarGridState = new AstarGridState(22, 10);
        AstarGridView astarGridView = new AstarGridView(astarGridState, 50);
        return astarGridView;
    }

    private void registerToolbarListener() {

    }

    private void setSideToolbar(ToolBar toolbar) {
        this.sideToolbar = toolbar;
    }
}
