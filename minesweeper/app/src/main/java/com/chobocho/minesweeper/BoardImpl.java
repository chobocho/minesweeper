package com.chobocho.minesweeper;

import java.util.Random;

public class BoardImpl implements Board {
    private String TAG = "BoardImpl";
    private int boardWidth = 10;
    private int boardHeight = 10;
    private int boomCount = 10;
    private int unopened_tile_count = 0;
    private int unusedFlagCount = 0;
    private int[][] board;
    private Tile[][] tiles;

    public BoardImpl(int boardWidth, int boardHeight, int boomCount) {
        setBoardInfo(boardWidth, boardHeight, boomCount);
    }

    public void setBoardInfo(int boardWidth, int boardHeight, int boomCount) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boomCount = boomCount;
        this.unusedFlagCount = boomCount;
        this.unopened_tile_count = boardWidth * boardWidth;
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
        this.unusedFlagCount = boomCount;
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

            if (!tiles[y][x].hasBoom()) {
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
    public int getUnusedFlagCount() {
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
        if (tiles[y][x].setOpen()) {
            unopened_tile_count--;
        }
        if (tiles[y][x].hasFlag()) {
            unusedFlagCount++;
        }
    }

    @Override
    public boolean hasBoom(int x, int y) {
        return tiles[y][x].hasBoom();
    }

    @Override
    public void setFlag(int x, int y) {
        if (tiles[y][x].isOpen()) {
            return;
        }
        if (tiles[y][x].hasFlag()) {
            tiles[y][x].setFlag(false);
            unusedFlagCount++;
        } else {
            tiles[y][x].setFlag(true);
            unusedFlagCount--;
        }
    }

    @Override
    public boolean isFinish() {
        return unopened_tile_count == boomCount;
    }
}