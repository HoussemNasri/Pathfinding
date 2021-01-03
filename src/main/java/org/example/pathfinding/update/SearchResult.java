package org.example.pathfinding.update;

import java.util.Objects;

import javafx.util.Duration;

import org.example.cell.Point;

public class SearchResult {
    private final Point startNode;
    private final Point targetNode;
    private final Duration elapsedTime;

    public SearchResult(Point startNode, Point targetNode, Duration elapsedTime) {
        this.startNode = startNode;
        this.targetNode = targetNode;
        this.elapsedTime = elapsedTime;
    }

    public Point getStartNode() {
        return startNode;
    }

    public Point getTargetNode() {
        return targetNode;
    }

    public Duration getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "startNode=" + startNode +
                ", targetNode=" + targetNode +
                ", elapsedTime=" + elapsedTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(startNode, that.startNode) && Objects.equals(targetNode, that.targetNode) && Objects.equals(elapsedTime, that.elapsedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startNode, targetNode, elapsedTime);
    }
}
