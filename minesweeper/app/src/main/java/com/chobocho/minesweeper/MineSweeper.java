package com.chobocho.minesweeper;

public interface MineSweeper {
    final int IDLE = 0;
    final int PLAY = 1;
    final int PAUSE = 2;
    final int WIN = 3;
    final int GAME_OVER = 4;

    final int EMPTY = 0;
    final int BOOM = 10;
    final int CRASH = 11;
    final int FLAG = 12;
    final int QUESTION = 13;
    final int NOT_OPEN = 14;

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

    public int[][] getBoard();
}