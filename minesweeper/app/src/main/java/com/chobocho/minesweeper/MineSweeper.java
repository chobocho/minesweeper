package com.chobocho.minesweeper;

public interface MineSweeper {
    final int IDLE = 0;
    final int PLAY = 1;
    final int PAUSE = 2;
    final int WIN = 3;
    final int GAME_OVER = 4;

    /**
     * 
     */
    public void register(GameObserver observer);

    /**
     * 
     */
    public void touch(int x, int y);

    /**
     * 
     */
    public void tick();

    /**
     * 
     */
    public void play();

    /**
     * 
     */
    public void pause();

    public void idle();

    public int getBoomCount();
}