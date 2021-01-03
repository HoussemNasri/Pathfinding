package org.example.algorithms;

public interface AlgorithmPlayer {
    void play();

    boolean isPlaying();

    void pause();

    void reset();

    void stepIn();

    void stepOut();
}
