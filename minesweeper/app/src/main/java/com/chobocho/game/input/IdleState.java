package com.chobocho.game.input;

import com.chobocho.game.BoardProfile;
import com.chobocho.minesweeper.MineSweeper;

public class IdleState extends State {

    public IdleState(BoardProfile profile, MineSweeper mineSweeper) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
    }

    @Override
    public void onTouch(int x, int y) {
        if (smile_button.in(x, y)) {
            mineSweeper.play();
        }
    }
}