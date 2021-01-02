package org.example.cell.view;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
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

        Transition typeTransition = null;
        if (cellType == CellType.WALL_CELL) {
            typeTransition = createWallTypeTransition(transitionNode);
        } else
            typeTransition = createDefaultTypeTransition(transitionNode);

        typeTransition.setOnFinished(e -> {
            switch (cellType) {
                case START_CELL -> customizeStartCell();
                case GOAL_CELL -> customizeGoalCell();
                case NORMAL_CELL -> customizeNormalCell();
                case WALL_CELL -> customizeWallCell();

                case OPEN_CELL -> customizeOpenCell();
                case CLOSE_CELL -> customizeCloseCell();
                case PATH_CELL -> customizePathCell();
            }
            view.getChildren().remove(transitionNode);
        });
    }

    private ScaleTransition createDefaultTypeTransition(Node animationNode) {
        view.getChildren().add(animationNode);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.10), animationNode);
        scaleTransition.setFromX(0.1);
        scaleTransition.setFromY(0.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.play();

        return scaleTransition;
    }

    private ScaleTransition createWallTypeTransition(Node animationNode) {
        view.getChildren().add(animationNode);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.10), animationNode);
        scaleTransition.setFromX(0.1);
        scaleTransition.setFromY(0.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setInterpolator(Interpolator.EASE_IN);
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
            case WALL_CELL -> "default-wall-cell-style";
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
        _setStyle(getOpenCellStyleClass());
    }

    private void customizeCloseCell() {
        _setStyle(getClosedCellStyleClass());
    }

    private void customizePathCell() {
        _setStyle(getPathCellStyleClass());
    }

    private String getPathCellStyleClass() {
        return "";
    }
}
