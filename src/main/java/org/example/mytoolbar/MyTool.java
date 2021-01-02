package org.example.mytoolbar;

public class MyTool {
    private final String name;
    private final String description;

    public MyTool(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "MyTool{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
