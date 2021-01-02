package com.chobocho.minesweeper;

public class GameInfo {
    private int gameState;
    private int playTime;
    private int unopenedTileCount;
    private int unusedFlagCount;
    private String board;

    public GameInfo(int width, int height) {
        board = "";
        playTime = 0;
        gameState = GameObserver.IDLE;
    }

    public String getBoard() {
        return this.board;
    }

    public void setBoard(char[] board) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < board.length; i++) {
            result.append(board[i]);
        }
        this.board = result.toString();
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public int getGameState() {
        return this.gameState;
    }

    public void setGameSate(int gameState) {
        this.gameState = gameState;
    }

    public int getUnopenedTileCount() {
        return this.unopenedTileCount;
    }

    public void setUnopenedTileCount(int count) {
        this.unopenedTileCount = count;
    }

    public int getUnusedFlagCount() {
        return this.unusedFlagCount;
    }

    public void setUnusedFlagCount(int count) {
        this.unusedFlagCount = count;
    }

    public int getPlayTime() {
        return this.playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
}
