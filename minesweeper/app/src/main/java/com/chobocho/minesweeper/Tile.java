package com.chobocho.minesweeper;

public class Tile {
    private boolean openState;
    private boolean flag;
    private boolean boom;
    private boolean explode;
    private int number = 0;

    public Tile() {
        init();
    }

    public void init() {
        openState = false;
        flag = false;
        boom = false;
        explode = false;
        number = 0;
    }

    public boolean isOpen() {
        return this.openState;
    }

    public void setBoom(){
        this.boom = true;
        number = 0;
    }

    public boolean hasBoom() {
        return this.boom;
    }

    public boolean isExploded() {
        return this.explode;
    }

    public boolean isEmpty() {
        return (!openState && !this.flag && !this.boom && (number == 0));
    }

    public void increaseNeighBoomCount() {
        if (hasBoom()) {
            return;
        }
        this.number++;
    }

    public boolean setOpen() {
        if (openState) {
            return false;
        }

        if (this.boom) {
            this.explode = true;
        }

        openState = true;
        flag = false;

        return true;
    }

    public void setClear() {
        if (openState) {
            return;
        }
        flag = false;
    }

    public boolean hasFlag() {
        return flag;
    }

    public void setFlag(boolean enable) {
        if (isOpen()) {
            return;
        }
        flag = enable;
    }

    public int toInt() {
        if (flag) {
            return MineSweeper.FLAG;
        }
        if (!openState) {
            return MineSweeper.NOT_OPEN;
        }
        if (explode) {
            return MineSweeper.CRASH;
        }
        if (boom) {
            return MineSweeper.BOOM;
        }
        return this.number;
    }

    public char toChar() {
        /*
           openState|hasFlag|explode|boom|number(4bit);
        */
        char result = 0x0;
        result = (char)(number & 0xF);
        result |= openState ? 0x80:0;
        result |= flag ? 0x40:0;
        result |= explode ? 0x20:0;
        result |= boom ? 0x10:0;
        return result;
    }

    public void setTile(char ch) {
        init();
        number = ch & 0xF;
        boom = (ch & 0x10) > 0;
        explode = (ch & 0x20) > 0;
        flag = (ch & 0x40) > 0;
        openState = (ch & 0x80) > 0;
    }
}
