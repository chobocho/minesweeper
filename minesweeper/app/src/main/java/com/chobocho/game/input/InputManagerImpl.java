package com.chobocho.game.input;

import android.util.Log;

import com.chobocho.game.BoardProfile;
import com.chobocho.minesweeper.GameObserver;
import com.chobocho.minesweeper.MineSweeper;

public class InputManagerImpl implements InputManager, GameObserver {
    final String TAG = "InputManagerImpl";
    BoardProfile mProfile;

    State gameState;
    State idleState;
    State playState;
    State pauseState;
    State winState;
    State gameoverState;

    public InputManagerImpl(BoardProfile profile, MineSweeper mineSweeper) {
        mProfile = profile;
        initState(mineSweeper);
        mineSweeper.register(this);
    }

    private void initState(MineSweeper mineSweeper) {
        idleState = new IdleState(mProfile, mineSweeper);
        playState = new PlayState(mProfile, mineSweeper);
        pauseState = new PauseState(mProfile, mineSweeper);
        winState = new WinState(mProfile, mineSweeper);
        gameoverState = new GameOverState(mProfile, mineSweeper);
        gameState = idleState;
    }


    @Override
    public void onTouch(int x, int y) {
        gameState.onTouch(x, y);
    }

    @Override
    public void Notify(int new_state) {
        Log.d(TAG, "New State:" + new_state);
        switch(new_state) {
            case GameObserver.IDLE:
                gameState = idleState;
                break;
            case GameObserver.PLAY:
                gameState = playState;
                break;
            case GameObserver.PAUSE:
                gameState = pauseState;
                break;
            case GameObserver.WIN:
                gameState = winState;
                break;
            case GameObserver.GAME_OVER:
                gameState = gameoverState;
                break;
            default:
                Log.e(TAG,"Error! Unexpected state");
                gameState = idleState;
                break;
        }
    }
}
