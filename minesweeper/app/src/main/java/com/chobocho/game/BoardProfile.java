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

    private int mBoardWidth = 0;
    private int mBoardHeight = 0;

    String version ="";

    public BoardProfile(String version, int width, int height) {
        this.version = version;
        setScreenSize(width, height);
        init();
    }

    private void init(){
        mBoardWidth = easy_board_width;
        mBoardHeight = easy_board_height;

        int block_width = (int) (screenW / 10);
        int block_height = (int) (screenH / (10+4));
        mblockSize = Math.min(block_width, block_height);

        mStartX = (int)((screenW - mblockSize*10)/2);
        mStartY = 0;
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
