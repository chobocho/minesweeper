package com.chobocho.game.ui;

import android.graphics.Canvas;

import com.chobocho.game.MineSweeperView;

public interface UiManager {
    int FLAG = 12;
    int QUESTION = 13;
    int COVER = 14;

    int IDLE_FACE = 15;
    int SMILE_FACE = 16;
    int BOOM_FACE = 17;
    int WIN_FACE = 18;
    int PAUSE_FACE = 19;
    int NEW_GAME = 20;
    int RESUME = 21;
    int NUMBER_0 = 22;

    public void setListener(MineSweeperView.ViewListener listener);
    public void onDraw(Canvas canvas);
}
