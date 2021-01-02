package org.example;

import javafx.scene.image.Image;

public class ToolbarIconProvider {
    public static Image getClearToolIcon(boolean isSelected) {
        if (isSelected) {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_broom_48px_black.png").toExternalForm());
        } else {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_broom_48px_white.png").toExternalForm());
        }
    }

    public static Image getWallToolIcon(boolean isSelected) {
        if (isSelected) {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_fence_48px_black.png").toExternalForm());
        } else {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_fence_48px_white.png").toExternalForm());
        }
    }

    public static Image getGoalMarkerToolIcon(boolean isSelected) {
        if (isSelected) {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_destination_48px_black.png").toExternalForm());
        } else {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_destination_48px_white.png").toExternalForm());
        }
    }

    public static Image getStartMarkerToolIcon(boolean isSelected) {
        if (isSelected) {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_home_address_48px_black.png").toExternalForm());
        } else {
            return new Image(ToolbarIconProvider.class.getResource("/icons8_home_address_48px_white.png").toExternalForm());
        }
    }

    public static Image getPlayPauseButtonIcon(boolean isPlaying) {
        return null;
    }
}
