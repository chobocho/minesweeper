package com.chobocho.minesweeper;

import java.util.ArrayList;

public class MineSweeperImpl implements MineSweeper, MineSweeperNotifyCallback {
    private ArrayList<GameObserver> observers;
    private int boardWidth;
    private int boardHeight;
    private int boomCount;
    private int play_time = 0;

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
        playState = new PlayState(this, board);
        pauseState = new PauseState();
        gameOverState = new GameOverState();
        winState = new WinState();

        gameState = idleState;
    }

    private void initGame() {
        play_time = 0;
        board.init();
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

    @Override
    public void open(int x, int y) {
        gameState.open(x, y);
    }

    @Override
    public void setFlag(int x, int y) {
        gameState.setFlag(x, y);
    }

    @Override
    public void tick() {
        play_time++;
    }

    @Override
    public int getPlaytime() {
        return play_time;
    }

    public void idle() {
        gameState = idleState;
        initGame();
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
    public void setWinState() {
        gameState = winState;
        Notify();
    }

    @Override
    public void setGameOverState() {
        gameState = gameOverState;
        Notify();
    }

    @Override
    public int getUnusedFlagCount() {
        return gameState.getBoomCount();
    }

    @Override
    public int[][] getBoard() {
        return board.getBoard();
    }
}