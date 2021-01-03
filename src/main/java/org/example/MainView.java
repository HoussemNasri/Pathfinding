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
import org.example.providers.PathfindingGridViewProvider;
import org.example.grid.view.grid.BaseGridviewWrapper;

public class MainView extends HBox {
    private ToolBar sideToolbar;
    private GridPane gridView;
    private BaseGridviewWrapper<?, ?> baseGridViewWrapper;
    private final ViewTuple<MyToolbarView, MyToolbarViewModel> toolbarTuple;

    public MainView() {
        this.toolbarTuple = createToolbarTuple();
        setPathfindingGridView(PathfindingGridViewProvider.getAstarGridView());
        setSideToolbar(toolbarView());
        initLayout();

        baseGridViewWrapper.registerCellClickedListener(toolbarViewModel());
        baseGridViewWrapper.registerCellDraggedOverListener(toolbarViewModel());

        toolbarViewModel().selectedAlgorithmProperty().addListener(this::onNewAlgorithmSelected);
    }

    private void initLayout() {
        getChildren().add(sideToolbar);
        getChildren().add(gridView);
    }

    public void setPathfindingGridView(BaseGridviewWrapper<?, ?> baseGridViewWrapper) {
        this.baseGridViewWrapper = baseGridViewWrapper;
        gridView = baseGridViewWrapper.getView();
    }

    private ViewTuple<MyToolbarView, MyToolbarViewModel> createToolbarTuple() {
        return FluentViewLoader.fxmlView(MyToolbarView.class).load();
    }

    private ToolBar toolbarView() {
        return (ToolBar) toolbarTuple.getView();
    }

    private MyToolbarViewModel toolbarViewModel() {
        return toolbarTuple.getViewModel();
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
