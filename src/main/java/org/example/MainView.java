package org.example;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import org.example.cell.AstarCell;
import org.example.cell.state.PathfindingGridState;
import org.example.mytoolbar.MyToolbarView;
import org.example.mytoolbar.MyToolbarViewModel;
import org.example.renderer.AstarGridView;
import org.example.renderer.PathfindingGridView;

public class MainView extends HBox {
    private ToolBar sideToolbar;
    private GridPane gridView;
    private PathfindingGridView<?, ?, ?> pathfindingGridView;
    private final ViewTuple<MyToolbarView, MyToolbarViewModel> toolbarTuple;

    public MainView() {
        setPathfindingGridView(createPathfindingGridView());
        toolbarTuple = createToolbarTuple();
        setSideToolbar(getToolbarView());
        initLayout();

        pathfindingGridView.registerCellClickedListener(getMyToolbarViewModel());
        pathfindingGridView.registerCellDraggedOverListener(getMyToolbarViewModel());
    }

    private void initLayout() {
        getChildren().add(sideToolbar);
        getChildren().add(gridView);
    }

    public void setPathfindingGridView(PathfindingGridView<?, ?, ?> pathfindingGridView) {
        this.pathfindingGridView = pathfindingGridView;
        gridView = pathfindingGridView.getView();
    }

    private ViewTuple<MyToolbarView, MyToolbarViewModel> createToolbarTuple() {
        return FluentViewLoader.fxmlView(MyToolbarView.class).load();
    }

    private ToolBar getToolbarView() {
        return (ToolBar) toolbarTuple.getView();
    }

    private MyToolbarViewModel getMyToolbarViewModel() {
        return toolbarTuple.getViewModel();
    }

    private PathfindingGridView<?, ?, ?> createPathfindingGridView() {
        PathfindingGridState<?> pathfindingGridState = new PathfindingGridState<>(AstarCell.class, 30, 16);
        AstarGridView astarGridView = new AstarGridView(pathfindingGridState, 40);
        return astarGridView;
    }

    private void registerToolbarListener() {

    }

    private void setSideToolbar(ToolBar toolbar) {
        this.sideToolbar = toolbar;
    }
}
