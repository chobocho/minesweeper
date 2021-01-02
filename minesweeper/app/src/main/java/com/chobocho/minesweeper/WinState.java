package com.chobocho.minesweeper;

public class WinState extends State {

    /**
     * Default constructor
     */
    public WinState() {
    }

    public int getState() {
        return GameObserver.WIN;
    }
}