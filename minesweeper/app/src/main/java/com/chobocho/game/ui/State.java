package com.chobocho.game.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.chobocho.minesweeper.MineSweeper;

public class State {
    protected  Bitmap[] images;

    public State() {

    }

    public void onDraw(Canvas canvas) {

    }

    protected int getImageNumber(int[][] board, int i, int j) {
        return board[j][i];
    }
}