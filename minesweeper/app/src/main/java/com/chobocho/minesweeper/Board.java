package com.chobocho.minesweeper;

public interface Board {
    public void init();
    public void setBoardInfo(int boardWidth, int boardHeight, int boomCount);
    public int getFlagCount();
    public boolean isOpen(int x, int y);
    public void open(int x, int y);
    public void clearTile(int x, int y);
    public void setFlag(int x, int y);
    public void setQuestion(int x, int y);
    public boolean isBoom(int x, int y);
    public boolean isFinish();
    public int[][] getBoard();
}