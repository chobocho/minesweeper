package com.chobocho.game;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chobocho.game.ui.UiManager;
import com.chobocho.game.ui.UiManagerImpl;


public class MineSweeperView extends View {
    private String LOG_TAG = this.getClass().getName();
    private Context mContext;
    private UiManager uiManager;


    public MineSweeperView(Context context, UiManager uiManagerImpl) {
        super(context);
        this.mContext = context;
        this.uiManager = uiManagerImpl;
    }


    public void onDraw(Canvas canvas) {
       this.uiManager.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LOG_TAG, ">> X: " + event.getX() + " Y: " + event.getY());

        return true;
    }

    public void onTouchReleased(int mouseX, int mouseY) {
        Log.i(LOG_TAG, "Mouse released " + mouseX + ":" + mouseY);
    }

    public void pauseGame() {

    }

    public void resumeGame() {

    }

}
