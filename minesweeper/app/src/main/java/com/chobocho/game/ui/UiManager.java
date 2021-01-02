package com.chobocho.game.ui;

import android.graphics.Canvas;

public interface UiManager {
    int FLAG = 12;
    int QUESTION = 13;
    int COVER = 14;

    int IDLE_FACE = 15;
    int SMILE_FACE = 16;
    int BOOM_FACE = 17;
    int WIN_FACE = 18;
    int PAUSE_FACE = 19;

    int NUMBER_0 = 20;
    public void onDraw(Canvas canvas);
}
