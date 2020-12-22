package com.chobocho.minesweeper;

public interface GameObserver {
    final int IDLE = 0;
    final int PLAY = 1;
    final int PAUSE = 2;
    final int WIN = 3;
    final int GAME_OVER = 4;

    public void Notify(int state);
}