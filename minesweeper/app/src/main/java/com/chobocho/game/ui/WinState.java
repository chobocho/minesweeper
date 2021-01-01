package com.chobocho.game.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.chobocho.game.BoardProfile;

public class WinState extends State {
    BoardProfile mProfile;
    Bitmap[] images;
    Paint paint;

    public WinState(BoardProfile profile, Bitmap[] images) {
        this.mProfile = profile;
        this.paint = new Paint();
        this.images = images;
    }

    public void onDraw(Canvas canvas) {
        int BLOCK_IMAGE_SIZE = mProfile.blockSize();
        int startX = mProfile.startX();
        int startY = mProfile.startY() + BLOCK_IMAGE_SIZE * 2;
        int boardW = mProfile.boardWidth();
        int boardH = mProfile.boardHeight();

        paint.setColor(Color.LTGRAY);
        canvas.drawRect(new Rect(0, 0, mProfile.screenWidth(), mProfile.screenHeight()), paint);

        for (int i = 0; i < boardW; i++) {
            for (int j = 0; j < boardH; j++) {
                canvas.drawBitmap(images[0], null,
                        new Rect(i * BLOCK_IMAGE_SIZE + startX,
                                j * BLOCK_IMAGE_SIZE + startY,
                                i * BLOCK_IMAGE_SIZE + startX + BLOCK_IMAGE_SIZE,
                                j * BLOCK_IMAGE_SIZE + startY + BLOCK_IMAGE_SIZE), paint);

            }
        }
    }
}