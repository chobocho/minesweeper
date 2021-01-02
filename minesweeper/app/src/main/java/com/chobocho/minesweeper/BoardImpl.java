package com.chobocho.minesweeper;

import android.util.Log;

import java.util.Random;
import java.util.Stack;

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
        this.unopened_tile_count = boardWidth * boardHeight;
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
        this.unopened_tile_count = this.boardWidth * this.boardHeight;
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
        return unusedFlagCount;
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
        if (tiles[y][x].hasFlag()) {
            tiles[y][x].setFlag(false);
            incUnusedFlagCount();
        }
        if (tiles[y][x].isEmpty()) {
            openTiles(x, y);
            return;
        }
        if (tiles[y][x].setOpen()) {
            unopened_tile_count--;
        }
    }

    private synchronized void openTiles(int x, int y) {
        Stack<Point> tileList = new Stack<>();
        tileList.push(new Point(x, y));

        while(!tileList.isEmpty()) {
            Point p = tileList.pop();
            if (!tiles[p.y][p.x].isEmpty()) {
                continue;
            }

            if (tiles[p.y][p.x].hasFlag()) {
                incUnusedFlagCount();
            }

            if (tiles[p.y][p.x].setOpen()) {
                unopened_tile_count--;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (((p.x+i) < 0) || ((p.y+j) < 0) || ((p.x+i) >= boardWidth) || ((p.y+j) >= boardHeight)) {
                        continue;
                    }
                    int nx = p.x+i;
                    int ny = p.y+j;
                    if (tiles[ny][nx].isEmpty()) {
                        tileList.push(new Point(nx, ny));
                    }
                    else if (!tiles[ny][nx].isOpen() && !tiles[ny][nx].hasBoom()) {
                        if (tiles[ny][nx].hasFlag()) {
                            incUnusedFlagCount();
                        }
                        if (tiles[ny][nx].setOpen()) {
                            unopened_tile_count--;
                        }
                    }
                }
            }
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
            incUnusedFlagCount();
        } else {
            if (unusedFlagCount <= 0) {
                return;
            }
            tiles[y][x].setFlag(true);
            decUnusedFlagCount();
        }
    }

    private void decUnusedFlagCount() {
        if (unusedFlagCount <= 0) {
            return;
        }
        unusedFlagCount--;
    }

    private void incUnusedFlagCount() {
        if (unusedFlagCount >= boomCount) {
            return;
        }
        unusedFlagCount++;
    }

    @Override
    public boolean isFinish() {
        Log.d(TAG, "unopened_tile_count: " + unopened_tile_count + " boomCount: " + boomCount);
        return unopened_tile_count == boomCount;
    }

    class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}