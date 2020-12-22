package com.chobocho.minesweeper;

import java.util.ArrayList;

public class MineSweeperImpl implements MineSweeper {
    private ArrayList<GameObserver> observers;
    State gameState;
    State idleState;
    State playState;
    State pauseState;
    State gameOverState;
    State winState;

    public MineSweeperImpl() {
        init();
    }

    public void init() {
        observers = new ArrayList<>();

        idleState = new IdleState();
        playState = new PlayState(10);
        pauseState = new PauseState();
        gameOverState = new GameOverState();
        winState = new WinState();

        gameState = idleState;
    }

    public void register(GameObserver gameObserver) {
        observers.add(gameObserver);
        gameObserver.Notify(gameState.getState());
    }

    private synchronized void Notify() {
        for (GameObserver observer:observers) {
            observer.Notify(gameState.getState());
        }
    }

    public void touch(int x, int y) {
        gameState.touch(x, y);
    }

    public void tick() {
        // TODO implement here
    }

    public void idle() {
        gameState = idleState;
        Notify();
    }

    public void play() {
        gameState = playState;
        Notify();
    }

    public void pause() {
        gameState = playState;
        Notify();
    }

    @Override
    public int getBoomCount() {
        return gameState.getBoomCount();
    }
}