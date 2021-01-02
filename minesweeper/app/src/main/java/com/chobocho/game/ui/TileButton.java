package com.chobocho.game.ui;

import android.graphics.Rect;

public class TileButton {
    String name = "";
    int code = 0;
    int startX = 0;
    int startY = 0;
    int endX = 0;
    int endY = 0;

    public TileButton(String name, int code, int startX, int startY, int width, int height) {
        this.name = name;
        this.code = code;
        this.startX = startX;
        this.startY = startY;
        this.endX = startX + width;
        this.endY = startY + height;
    }

    public boolean in(int x, int y) {
        if (x < this.startX) return false;
        if (x > this.endX) return false;
        if (y < this.startY) return false;
        if (y > this.endY) return false;
        return true;
    }

    public int x1() { return this.startX; }
    public int x2() { return this.endX; }
    public int y1() { return this.startY; }
    public int y2() { return this.endY; }

    public Rect toRect() {
        return new Rect(startX, startY, endX, endY);
    }
}
