package org.example.toolbar;

import javafx.scene.Cursor;

import com.sun.javafx.cursor.CursorType;

public class Tool {
    public final static String NO_DESCRIPTION = "'";
    public static final String PLACEHOLDER_ICON = "";

    private final String name;
    private final String description;
    private final String iconUri;
    private final Cursor cursor;

    public Tool(String name, String description, String iconUri, Cursor cursor) {
        this.name = name;
        this.description = description;
        this.iconUri = iconUri;
        this.cursor = cursor;
    }

    public Tool(String name, String description, String iconUri) {
        this(name, description, iconUri, Cursor.DEFAULT);
    }

    public Tool(String name, String description) {
        this(name, description, PLACEHOLDER_ICON);
    }

    public static String getNoDescription() {
        return NO_DESCRIPTION;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconUri() {
        return iconUri;
    }

    public Cursor getCursor() {
        return cursor;
    }
}
