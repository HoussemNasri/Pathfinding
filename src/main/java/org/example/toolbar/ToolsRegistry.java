package org.example.toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ToolsRegistry {
    private final static List<MyTool> tools = new ArrayList<>();

    static {
        tools.add(new MyTool("Wall Tool", ""));
        tools.add(new MyTool("Home Marker Tool", ""));
        tools.add(new MyTool("Destination Marker Tool", ""));
    }

    public static List<MyTool> getTools() {
        return Collections.unmodifiableList(tools);
    }

    public static MyTool lookup(GridTool enumTool) {
        return tools.get(lookupIndex(enumTool));
    }

    public static int lookupIndex(GridTool gridTool) {
        return Arrays.asList(GridTool.values()).indexOf(gridTool);
    }
}
