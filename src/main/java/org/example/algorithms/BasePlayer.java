package org.example.algorithms;

import javafx.beans.property.BooleanProperty;

public interface BasePlayer {
    void play();

    BooleanProperty isPlayingProperty();

    void pause();

    void reset();

    void stepIn();

    void stepOut();
}
