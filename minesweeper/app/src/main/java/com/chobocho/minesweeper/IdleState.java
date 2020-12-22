package com.chobocho.minesweeper;

public class IdleState extends State {

    /**
     * Default constructor
     */
    public IdleState() {
    }

    public int getState() {
        return GameObserver.IDLE;
    }
}