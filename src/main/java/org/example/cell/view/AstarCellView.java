package org.example.cell.view;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.example.cell.AstarCell;
import org.example.cell.CellType;

public class AstarCellView extends AbstractCellView<AstarCell> {

    public AstarCellView(double size, AstarCell cellModel) {
        super(size, cellModel);
        customizeCell(null, null, cellModel.getType());
        cellModel.cellTypeProperty().addListener(this::customizeCell);
    }

    private void customizeCell(Observable o, CellType ol, CellType cellType) {
        Node transitionNode = createTransitionNode(cellType);
        ScaleTransition typeTransition = createTypeTransitionAnimation(transitionNode);
        getChildren().add(transitionNode);

        typeTransition.setOnFinished(e -> {
            System.out.println(":"+cellType);
            switch (cellType) {
                case START_CELL -> customizeStartCell();
                case GOAL_CELL -> customizeGoalCell();
                case NORMAL_CELL -> customizeNormalCell();
                case WALL_CELL -> customizeWallCell();

                case OPEN_CELL -> customizeOpenCell();
                case CLOSE_CELL -> customizeCloseCell();
                case PATH_CELL -> customizePathCell();
            }
            getChildren().remove(transitionNode);
        });
    }

    private ScaleTransition createTypeTransitionAnimation(Node animationNode) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.10), animationNode);
        scaleTransition.setFromX(0.1);
        scaleTransition.setFromY(0.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.play();

        return scaleTransition;
    }

    private Node createTransitionNode(CellType cellType) {
        StackPane transtionNode = new StackPane();
        transtionNode.getStyleClass().setAll(cellStyleResolver(cellType));
        return transtionNode;
    }

    private String cellStyleResolver(CellType cellType) {
        return switch (cellType) {
            case NORMAL_CELL -> "default-normal-cell-style";
            case WALL_CELL -> "";
            case START_CELL -> "default-start-cell-style";
            case GOAL_CELL -> "default-goal-cell-style";
            default -> "";
        };
    }

    private String getOpenCellStyleClass() {
        return "";
    }

    private String getClosedCellStyleClass() {
        return "";
    }

    private void customizeOpenCell() {
        getStyleClass().add("astar-open-cell-style");
    }

    private void customizeCloseCell() {
        getStyleClass().add("astar-close-cell-style");
    }

    private void customizePathCell() {
        getStyleClass().add("astar-path-cell-style");
    }

    private void customizeHomePlaceholderCell() {
        setStyle("-fx-background-color: #e3f51d");
    }
}
