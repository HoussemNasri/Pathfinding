package org.example.algorithms;

public class PathfindingAlgorithmModel {
    private String name;
    private String fullName;
    private String description;

    public PathfindingAlgorithmModel(String name, String fullName, String description) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
    }

    public PathfindingAlgorithmModel(String name, String fullName) {
        this(name, fullName, "");
    }

    public PathfindingAlgorithmModel(String name) {
        this(name, name);
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PathfindingAlgorithmModel{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
