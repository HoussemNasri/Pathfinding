package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.example.grid.GridState;
import org.example.pathfinding.AStartAlgorithm;
import org.example.grid.GridView;
import org.example.style.AStarCellStyler;
import org.example.style.StandardCellStyler;
import org.example.style.StartGoalStyler;
import org.example.toolbar.Toolbar;
import org.example.toolbar.ToolbarView;

public class MainApp extends Application {

    private void startExploring(AStartAlgorithm aStartAlgorithm) {
        long delay = (long) 0e8;
        AnimationTimer timer = new AnimationTimer() {
            private long start = -1;
            @Override
            public void handle(long now) {
                if (start == -1 || now - start >= delay) {
                    start = now;
                    if (aStartAlgorithm.step()) {
                        this.stop();
                    }
                }
            }
        };
        timer.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridState gridState = new GridState(150, 70);
        GridView gridRenderer = new GridView(gridState, 9);
        GridPane gridPane = gridRenderer.getView();

        gridRenderer.registerStyler(StartGoalStyler.getInstance());
        gridRenderer.registerStyler(StandardCellStyler.getInstance());
        gridRenderer.registerStyler(AStarCellStyler.getInstance());

        VBox root = new VBox();

        Toolbar toolbar = Toolbar.getDefaultToolbar();
        ToolbarView toolbarView = new ToolbarView(toolbar);

        AStartAlgorithm aStartAlgorithm = new AStartAlgorithm(gridState);

        toolbarView.setOnMouseClicked(e -> startExploring(aStartAlgorithm));

        root.getChildren().add(toolbarView);
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles.css").toExternalForm());

        stage.setTitle("Pathfinding");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}