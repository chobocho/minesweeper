package com.chobocho.minesweeper;

public class PlayState extends State {
    Board board;
    MineSweeperNotifyCallback minesweeper;

    public PlayState(MineSweeperNotifyCallback mineSweeper, Board board) {
        this.minesweeper = mineSweeper;
        this.board = board;
    }

    public int getState() {
        return GameObserver.PLAY;
    }

    @Override
    public void open(int x, int y) {
        this.board.open(x, y);
        if (board.isFinish()) {
            minesweeper.setWinState();
        } else if (board.hasBoom(x, y)) {
            minesweeper.setGameOverState();
        }
    }

    @Override
    public void setFlag(int x, int y) {
        this.board.setFlag(x, y);
    }
}