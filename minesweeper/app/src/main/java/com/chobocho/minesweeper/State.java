package com.chobocho.minesweeper;

public class State {
    protected int mBoomCount = 0;

    public State() {
    }

    public void setFlag(int x, int y) {

    }

    public void open(int x, int y) {

    }

    public int getState() {
        return -1;
    }

    public int getBoomCount() {
        return mBoomCount;
    }
}