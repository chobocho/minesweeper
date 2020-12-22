package com.chobocho.minesweeper;

public class PauseState extends State {

    /**
     * Default constructor
     */
    public PauseState() {
    }

    public int getState() {
        return GameObserver.PAUSE;
    }
}