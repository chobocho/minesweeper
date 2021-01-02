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


    public void register(GameObserver observer);
    public GameInfo getGameInfo();
    public void setGameInfo(GameInfo gameInfo);

    public boolean isPlayState();

    public void setFlag(int x, int y);
    public int getUnusedFlagCount();
    public void open(int x, int y);

    public void tick();
    public int getPlaytime();

    public void play();
    public void pause();
    public void idle();

    public void toggleTool();
    public int getToolType();

    public int[][] getBoard();
}