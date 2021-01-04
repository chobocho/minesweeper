package com.chobocho.game.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.chobocho.game.BoardProfile;
import com.chobocho.minesweeper.MineSweeper;

public class IdleState extends State {

    public IdleState(BoardProfile profile, MineSweeper mineSweeper, Bitmap[] images) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
        this.paint = new Paint();
        this.images = images;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawSmileButton(UiManager.IDLE_FACE, canvas, paint);
        canvas.drawBitmap(images[UiManager.START_GAME], null, start_game_button.toRect(), paint);
    }
}