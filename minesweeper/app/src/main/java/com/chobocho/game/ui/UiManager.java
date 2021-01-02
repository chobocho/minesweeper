package com.chobocho.game.ui;

import android.graphics.Canvas;

public interface UiManager {
    int FLAG = 12;
    int QUESTION = 13;
    int COVER = 14;

    int SMILE_FACE = 15;
    int BOOM_FACE = 16;
    int WIN_FACE = 17;
    int NUMBER_0 = 18;
    public void onDraw(Canvas canvas);
}
