package org.example.grid.view.cell;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.example.grid.cell.AstarCell;
import org.example.grid.cell.CellType;

public class AstarCellView extends BaseCellView<AstarCell> {
    public AstarCellView(double size, AstarCell cellModel) {
        super(size, cellModel);
        customizeCell(null, null, cellModel.getType());
        cellModel.cellTypeProperty().addListener(this::customizeCell);
    }

    private void customizeCell(Observable o, CellType ol, CellType cellType) {
        System.out.println(cellType);
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
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), animationNode);
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
            case NORMAL_CELL -> getNormalCellStyleClass();
            case WALL_CELL -> getWallStyleClass();
            case START_CELL -> getHomeCellStyleClass();
            case GOAL_CELL -> getDestinationCelLStyleClass();
            case OPEN_CELL -> getOpenCellStyleClass();
            case CLOSE_CELL -> getClosedCellStyleClass();
            case PATH_CELL -> getPathCellStyleClass();
            default -> "";
        };
    }

    private String getOpenCellStyleClass() {
        return "astar-open-cell-style";
    }

    private String getClosedCellStyleClass() {
        return "astar-close-cell-style";
    }

    private void customizeOpenCell() {
        setView(new OpenCellView(cellModel.getCost()));
    }

    private void customizeCloseCell() {
        setView(new SimpleCellView(getClosedCellStyleClass()));
        //_setStyle(getClosedCellStyleClass());
    }

    private void customizePathCell() {
        setView(new SimpleCellView(getPathCellStyleClass()));
        /*  this.view.getChildren().clear();
        _setStyle(getPathCellStyleClass());*/
    }

    private String getPathCellStyleClass() {
        return "astar-path-cell-style";
    }
}
