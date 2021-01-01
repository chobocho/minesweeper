package com.chobocho.minesweeper;

import java.util.ArrayList;

public class MineSweeperImpl implements MineSweeper {
    private ArrayList<GameObserver> observers;
    private int boardWidth;
    private int boardHeight;
    private int boomCount;

    Board board;
    State gameState;
    State idleState;
    State playState;
    State pauseState;
    State gameOverState;
    State winState;

    public MineSweeperImpl(int boardWidth, int boardHeight, int boomCount) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boomCount = boomCount;
        init();
    }

    public void setBoardInfo(int boardWidth, int boardHeight, int boomCount) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boomCount = boomCount;
        this.board.setBoardInfo(boardWidth, boardHeight, boomCount);
    }

    public void init() {
        observers = new ArrayList<>();

        board = new BoardImpl(boardWidth, boardHeight, boomCount);

        idleState = new IdleState();
        playState = new PlayState(boomCount);
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

    @Override
    public int[][] getBoard() {
        return board.getBoard();
    }
}