package com.chobocho.game.input;
import com.chobocho.game.BoardProfile;
import com.chobocho.game.TileButton;
import com.chobocho.minesweeper.MineSweeper;

public class State {

    protected TileButton smile_button;
    protected  TileButton[] play_time;
    protected  TileButton flag_button;
    protected  TileButton[] flag_count_button;

    MineSweeper  mineSweeper;
    BoardProfile mProfile;

    public State(BoardProfile profile) {
         init(profile);
    }

    protected void init(BoardProfile profile) {

        int playtime_startX = profile.playtimeBtnStartX();
        int playtime_startY = profile.playtimeBtnStartY();
        int playtime_w = profile.playtimeBtnWidth();
        int playtime_h = profile.playtimeBtnHeight();
        play_time = new TileButton[4];
        for (int i = 0; i < 4; i++) {
            play_time[i] = new TileButton("", 0, playtime_startX+playtime_w*i, playtime_startY, playtime_w, playtime_h);
        }

        int smile_startX = profile.smileBtnStartX();
        int smile_startY = profile.smileBtnStartY();
        int smile_w = profile.smileBtnWidth();
        int smile_h = profile.smileBtnHeight();
        smile_button = new TileButton("", 0, smile_startX, smile_startY, smile_w, smile_h);

        int flag_startX = profile.flagBtnStartX();
        int flag_startY = profile.flagBtnStartY();
        int flag_w = profile.flagBtnWidth();
        int flag_h = profile.flagBtnHeight();
        flag_button = new TileButton("", 0, flag_startX, flag_startY, flag_w, flag_h);

        int flag_count_startX = profile.flagCountBtnStartX();
        int flag_count_startY = profile.flagCountBtnStartY();
        int flag_count_w = profile.flagCountBtnWidth();
        int flag_count_h = profile.flagCountBtnHeight();
        flag_count_button = new TileButton[2];
        for (int i = 0; i < 2; i++) {
            flag_count_button[i] = new TileButton("", 0, flag_count_startX+flag_count_w*i, flag_count_startY, flag_count_w, flag_count_h);
        }
    }

    public void onTouch(int x, int y) {

    }

}