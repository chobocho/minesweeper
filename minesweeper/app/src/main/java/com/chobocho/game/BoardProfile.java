package com.chobocho.game;

public class BoardProfile {
    private static String TAG = "BoardProfile";
    private final int easy_board_width = 10;
    private final int easy_board_height = 10;

    private static int screenW = 1080;
    private static int screenH = 1820;
    private int mblockSize = 120;

    private int mStartX = 0;
    private int mStartY = 0;

    private int mTileStartX = 0;
    private int mTileStartY = 0;

    private int mBoardWidth = 0;
    private int mBoardHeight = 0;

    private int playtime_startX = 0;
    private int playtime_startY = 0;
    private int playtime_button_width = 0;
    private int playtime_button_height = 0;

    private int smile_button_startX = 0;
    private int smile_button_startY = 0;
    private int smile_button_width = 0;
    private int smile_button_height = 0;

    private int flag_button_startX = 0;
    private int flag_button_startY = 0;
    private int flag_button_width = 0;
    private int flag_button_height = 0;

    private int flag_count_button_startX = 0;
    private int flag_count_button_startY = 0;
    private int flag_count_button_width = 0;
    private int flag_count_button_height = 0;

    private int new_game_button_startX = 0;
    private int new_game_button_startY = 0;
    private int new_game_button_width = 0;
    private int new_game_button_height = 0;

    private int resume_button_startX = 0;
    private int resume_button_startY = 0;
    private int resume_button_width = 0;
    private int resume_button_height = 0;

    String version;

    public BoardProfile(String version, int width, int height) {
        this.version = version;
        setScreenSize(width, height);
        init();
    }

    private void init(){
        mBoardWidth = easy_board_width;
        mBoardHeight = easy_board_height;

        get_block_size();

        mStartX = (int)((screenW - mblockSize*10)/2);
        mStartY = 0;

        mTileStartX = mStartX;
        mTileStartY = mStartY + 2 * blockSize();

        init_smile_button();
        init_playtime_button();
        init_flag_button();
        init_new_game_button();
        init_resume_button();
    }

    public int tileStartX() { return mTileStartX; }
    public int tileStartY() { return mTileStartY; }

    private void get_block_size() {
        int block_width = (int) (screenW / 10);
        int block_height = (int) (screenH / (10+4));
        mblockSize = Math.min(block_width, block_height);
    }

    private void init_smile_button() {
        smile_button_startX = mStartX + blockSize()*4;
        smile_button_startY = mStartY;
        smile_button_width = blockSize() * 2;
        smile_button_height = blockSize() * 2;
    }

    public int smileBtnStartX() {
        return smile_button_startX;
    }

    public int smileBtnStartY() {
        return smile_button_startY;
    }

    public int smileBtnWidth() {
        return smile_button_width;
    }

    public int smileBtnHeight() {
        return smile_button_height;
    }

    private void init_playtime_button() {
        playtime_startX = mStartX;
        playtime_startY = mStartY;
        playtime_button_width = (int)(blockSize()/2);
        playtime_button_height = blockSize();
    }

    public int playtimeBtnStartX() {
        return playtime_startX;
    }

    public int playtimeBtnStartY() {
        return playtime_startY;
    }

    public int playtimeBtnWidth() {
        return playtime_button_width;
    }

    public int playtimeBtnHeight() {
        return playtime_button_height;
    }

    private void init_flag_button() {
        flag_button_startX = mStartX + blockSize()*7;
        flag_button_startY = mStartY;
        flag_button_width = blockSize();
        flag_button_height = blockSize();

        flag_count_button_startX = mStartX + blockSize()*9;
        flag_count_button_startY = mStartY;
        flag_count_button_width = (int)(blockSize()/2);
        flag_count_button_height = blockSize();
    }

    public int flagBtnStartX() {
        return flag_button_startX;
    }

    public int flagBtnStartY() {
        return flag_button_startY;
    }

    public int flagBtnWidth() {
        return flag_button_width;
    }

    public int flagBtnHeight() {
        return flag_button_height;
    }

    public int flagCountBtnStartX() {
        return flag_count_button_startX;
    }

    public int flagCountBtnStartY() {
        return flag_count_button_startY;
    }

    public int flagCountBtnWidth() {
        return flag_count_button_width;
    }

    public int flagCountBtnHeight() {
        return flag_count_button_height;
    }

    public void init_new_game_button() {
        new_game_button_startX = startX() + blockSize() * 3;
        new_game_button_startY = startY() + blockSize() * 7;
        new_game_button_width = blockSize()*4;
        new_game_button_height = blockSize();
    }

    public int newGameButtonStartX() {
        return new_game_button_startX;
    }

    public int newGameButtonStartY() {
        return new_game_button_startY;
    }

    public int newGameButtonWidth() {
        return new_game_button_width;
    }

    public int newGameButtonHeight() {
        return new_game_button_height;
    }

    public void init_resume_button() {
        resume_button_startX = startX() + blockSize() * 3;
        resume_button_startY = startY() + blockSize() * 4;
        resume_button_width = blockSize()*4;
        resume_button_height = blockSize();
    }

    public int resumeButtonStartX() {
        return resume_button_startX;
    }

    public int resumeButtonStartY() {
        return resume_button_startY;
    }

    public int resumeButtonWidth() {
        return resume_button_width;
    }

    public int resumeButtonHeight() {
        return resume_button_height;
    }

    public void setScreenSize(int w, int h) {
        this.screenW = w;
        this.screenH = h;
    }

    public int screenWidth() {
        return this.screenW;
    }
    public int screenHeight() {
        return this.screenH;
    }
    public int blockSize() { return this.mblockSize; }
    public int startX() { return this.mStartX; }
    public int startY() { return this.mStartY; }
    public int boardWidth() { return mBoardWidth; }
    public int boardHeight() { return mBoardHeight; }

    public String getVersion() {
        return version;
    }
}
