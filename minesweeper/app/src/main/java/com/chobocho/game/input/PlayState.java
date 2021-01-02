package com.chobocho.game.input;

import android.util.Log;

import com.chobocho.game.BoardProfile;
import com.chobocho.minesweeper.MineSweeper;

public class PlayState extends State {
    private String TAG = "InputPlayState";

    public PlayState(BoardProfile profile, MineSweeper mineSweeper) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
    }

    @Override
    public void onTouch(int x, int y) {
        if (smile_button.in(x, y)) {
            mineSweeper.pause();
            return;
        }
        if (flag_button.in(x, y)) {
            mineSweeper.toggleTool();
            return;
        }
        if (y > mProfile.tileStartY()) {
            Log.d(TAG, "X: " + x + ", Y: " + y);
            int sx = (int)((x - mProfile.tileStartX()) / mProfile.blockSize());
            int sy = (int)((y - mProfile.tileStartY()) / mProfile.blockSize());
            Log.i(TAG, "SX: " + sx + ", SY: " + sy);

            if (mineSweeper.getToolType() == MineSweeper.BOOM) {
                mineSweeper.open(sx, sy);
            } else {
                mineSweeper.setFlag(sx, sy);
            }
            return;
        }
    }
}