package com.chobocho.minesweeper;

public class PlayState extends State {

    public PlayState(int boomCount) {
        mBoomCount = boomCount;
    }

    public int getState() {
        return GameObserver.PLAY;
    }

    @Override
    public void touch(int x, int y) {

    }
}