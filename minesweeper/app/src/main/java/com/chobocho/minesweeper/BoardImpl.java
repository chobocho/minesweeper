package com.chobocho.minesweeper;

import java.util.Random;

public class BoardImpl implements Board {
    private String TAG = "BoardImpl";
    private int boardWidth = 10;
    private int boardHeight = 10;
    private int boomCount = 10;
    private int foundBoomCount = 0;
    private int flagCount = 0;
    private int[][] board;
    private Tile[][] tiles;

    public BoardImpl(int boardWidth, int boardHeight, int boomCount) {
        setBoardInfo(boardWidth, boardHeight, boomCount);
    }

    public void setBoardInfo(int boardWidth, int boardHeight, int boomCount) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boomCount = boomCount;
        initvar();
        init();
    }

    private void initvar() {
        board = new int[this.boardHeight+1][this.boardWidth+1];
        tiles = new Tile[this.boardHeight+1][this.boardWidth+1];

        for (int i = 0; i < this.boardHeight; i++) {
            for (int j = 0; j < this.boardHeight; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }

    public void init() {
        initBoard();
        initBoom();
    }

    private void initBoard() {
        for (int i = 0; i < this.boardHeight; i++) {
            for (int j = 0; j < this.boardHeight; j++) {
                tiles[i][j].init();
            }
        }
    }

    private void initBoom() {
        int count = this.boomCount;
        int max_count = 1000;

        Random rand = new Random();

        while (count > 0) {
            int x = rand.nextInt(boardWidth);
            int y = rand.nextInt(boardHeight);

            if (!tiles[y][x].isBoom()) {
                tiles[y][x].setBoom();
                updateBoomInfo(x, y);
                count--;
            } else {
                //Log.d(TAG,  "X: "+ x + ",Y: " + y);
            }
            max_count--;

            if (max_count < 0) {
                //Log.e(TAG, "Fail to assign all boom - BoomCount: " + boomCount + " , Result : " + (boomCount - count));
                break;
            }
        }
    }

    private void updateBoomInfo(int x, int y) {
        for (int i = -1; i <= 1; i++ ) {
            for (int j = -1; j <= 1; j++) {
                if (((x+i) < 0) || ((y+j) < 0) || ((x+i) >= boardWidth) || ((y+j) >= boardHeight)) {
                    continue;
                }
                tiles[y+j][x+i].increaseNeighBoomCount();
            }
        }
    }

    @Override
    public int getFlagCount() {
        return 0;
    }

    @Override
    public int[][] getBoard() {
        for (int i = 0; i < this.boardHeight; i++) {
            for (int j = 0; j < this.boardHeight; j++) {
                board[i][j] = tiles[i][j].toInt();
            }
        }

        return this.board;
    }

    @Override
    public boolean isOpen(int x, int y) {
        return tiles[y][x].isOpen();
    }

    @Override
    public void open(int x, int y) {
        tiles[y][x].setOpen();
    }

    @Override
    public boolean isBoom(int x, int y) {
        return tiles[y][x].isBoom();
    }

    @Override
    public void setFlag(int x, int y) {
        if (tiles[y][x].haveFlag()) {
            return;
        }
        tiles[y][x].setFlag(true);
        flagCount++;
        inc_boom_count(x, y);
    }

    private void inc_boom_count(int x, int y) {
        if (tiles[y][x].isBoom()) {
            foundBoomCount++;
        }
    }

    private void dec_boom_count(int x, int y) {
        if (tiles[y][x].isBoom()) {
            foundBoomCount--;
        }
    }

    @Override
    public void setQuestion(int x, int y) {
        if (tiles[y][x].haveFlag()) {
            dec_boom_count(x, y);
            flagCount--;
        }
        tiles[y][x].setQuestion(true);
    }


    @Override
    public void clearTile(int x, int y) {
        if (tiles[y][x].haveFlag()) {
            dec_boom_count(x, y);
            flagCount--;
        }
        tiles[y][x].setNothing();
    }

    @Override
    public boolean isFinish() {
        return foundBoomCount == boomCount;
    }
}