package org.example;

import javafx.beans.Observable;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import org.example.toolbar.MyToolbarView;
import org.example.toolbar.MyToolbarViewModel;
import org.example.toolbar.PathfindingAlgorithm;
import org.example.providers.PathfindingGridStateProvider;
import org.example.providers.PathfindingGridViewProvider;
import org.example.grid.view.grid.AstarGridView;
import org.example.grid.view.grid.BaseGridView;

public class MainView extends HBox {
    private ToolBar sideToolbar;
    private GridPane gridView;
    private BaseGridView<?, ?> baseGridView;
    private final ViewTuple<MyToolbarView, MyToolbarViewModel> toolbarTuple;

    public MainView() {
        setPathfindingGridView(PathfindingGridViewProvider.getAstarGridView());
        toolbarTuple = createToolbarTuple();
        setSideToolbar(getToolbarView());
        initLayout();

        baseGridView.registerCellClickedListener(getMyToolbarViewModel());
        baseGridView.registerCellDraggedOverListener(getMyToolbarViewModel());

        getMyToolbarViewModel().selectedAlgorithmProperty().addListener(this::onNewAlgorithmSelected);
    }

    private void initLayout() {
        getChildren().add(sideToolbar);
        getChildren().add(gridView);
    }

    public void setPathfindingGridView(BaseGridView<?, ?> baseGridView) {
        this.baseGridView = baseGridView;
        gridView = baseGridView.getView();
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

    private void registerToolbarListener() {

    }

    private void setSideToolbar(ToolBar toolbar) {
        this.sideToolbar = toolbar;
    }

    private void onNewAlgorithmSelected(Observable obs, PathfindingAlgorithm old, PathfindingAlgorithm algorithm) {
        if (algorithm == PathfindingAlgorithm.A_STAR) {
            setPathfindingGridView(PathfindingGridViewProvider.getAstarGridView());
        } else if (algorithm == PathfindingAlgorithm.DIJKSTRA) {
            setPathfindingGridView(PathfindingGridViewProvider.getDijkstraGridView());
        }
    }
}
