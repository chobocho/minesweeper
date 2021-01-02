package com.chobocho.minesweeper;

public class IdleState extends State {

    public IdleState() {
    }

    public int getState() {
        return GameObserver.IDLE;
    }
}