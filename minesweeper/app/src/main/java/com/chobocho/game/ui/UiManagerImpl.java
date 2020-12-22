package com.chobocho.game.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.chobocho.game.BoardProfile;
import com.chobocho.game.R;
import com.chobocho.minesweeper.GameObserver;
import com.chobocho.minesweeper.MineSweeper;

public class UiManagerImpl implements UiManager, GameObserver {
    final String TAG = "UiManagerImpl";
    Bitmap[] mTile;
    Context mContext;
    BoardProfile mProfile;


    State uiState;
    State idleState;
    State playState;
    State pauseState;
    State winState;
    State gameoverState;


    public UiManagerImpl(Context context, BoardProfile profile, MineSweeper mineSweeper) {
        mContext = context;
        mProfile = profile;
        init();
        loadImage();
        mineSweeper.register(this);
    }


    private void init() {
        idleState = new IdleState(mProfile);
        playState = new PlayState();
        pauseState = new PauseState();
        winState = new WinState();
        gameoverState = new GameOverState();
        uiState = idleState;
    }


    private void loadImage() {
        mTile = new Bitmap[10];
        mTile[0] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n0);
    }


    @Override
    public void Notify(int new_state) {
        Log.d(TAG, "New State:" + new_state);
        switch(new_state) {
            case GameObserver.IDLE:
                uiState = idleState;
                break;
            case GameObserver.PLAY:
                uiState = playState;
                break;
            case GameObserver.PAUSE:
                uiState = pauseState;
                break;
            case GameObserver.WIN:
                uiState = winState;
                break;
            case GameObserver.GAME_OVER:
                uiState = gameoverState;
                break;
            default:
                Log.e(TAG,"Error! Unexpected state");
                uiState = idleState;
                break;
        }
    }


    @Override
    public void onDraw(Canvas canvas) {
        uiState.onDraw2(canvas, mTile);
    }
}