package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.example.grid.Point;
import org.example.grid.cell.AstarCell;
import org.example.grid.cell.CellType;
import org.example.grid.state.PathfindingGridState;
import org.example.legacy.grid.GridState;
import org.example.legacy.pathfinding.AStartAlgorithm;
import org.example.legacy.grid.GridView;
import org.example.grid.view.grid.AstarGridviewWrapper;
import org.example.legacy.style.AStarCellStyler;
import org.example.legacy.style.StandardCellStyler;
import org.example.legacy.style.StartGoalStyler;
import org.example.legacy.toolbar.Toolbar;

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
       /* GridState gridState = new GridState(15, 10);
        GridView gridRenderer = new GridView(gridState, 70);
        GridPane gridPane = gridRenderer.getView();

        gridRenderer.registerStyler(StartGoalStyler.getInstance());
        gridRenderer.registerStyler(StandardCellStyler.getInstance());
        gridRenderer.registerStyler(AStarCellStyler.getInstance());

        VBox root = new VBox();

        Toolbar toolbar = Toolbar.getDefaultToolbar();

        AStartAlgorithm aStartAlgorithm = new AStartAlgorithm(gridState);
        //toolbarView.setOnMouseClicked(e -> startExploring(aStartAlgorithm));*/

        MainView mainView = new MainView();

        Group root = new Group();

        HBox hBox = new HBox();

        Button b = new Button("Click");
        ImageView imageView = new ImageView();
        ExampleCellView cellView = new ExampleCellView();
        hBox.getChildren().add(cellView);
        Scene scene = new Scene(hBox, 400, 400);
        scene.setOnMouseClicked(e -> {
            b.setSkin(new CustomButtonSkin(b));
        });
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles.css").toExternalForm());

        stage.setTitle("Pathfinding");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}