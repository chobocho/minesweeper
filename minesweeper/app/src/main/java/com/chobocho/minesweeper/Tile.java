package com.chobocho.minesweeper;

public class Tile {
    private boolean openState;
    private boolean hasFlag;
    private boolean hasQuestion;
    private boolean boom;
    private boolean explode;
    private int number = 0;

    public Tile() {
        init();
    }

    public void init() {
        openState = false;
        hasFlag = false;
        hasQuestion = false;
        boom = false;
        explode = false;
    }

    public boolean isOpen() {
        return this.openState;
    }

    public void setBoom(){
        this.boom = true;
        number = 0;
    }

    public boolean isBoom() {
        return this.boom;
    }

    public boolean isExploded() {
        return this.explode = true;
    }

    public void increaseNeighBoomCount() {
        if (this.boom) {
            return;
        }
        this.number++;
    }

    public void setOpen() {
        if (openState) {
            return;
        }

        if (this.boom) {
            this.explode = true;
        }

        openState = true;
        hasFlag = false;
        hasQuestion = false;
    }

    public void setNothing() {
        if (openState) {
            return;
        }
        hasFlag = false;
        hasQuestion = false;
    }

    public boolean haveFlag() {
        return hasFlag;
    }

    public void setFlag(boolean enable) {
        if (openState) {
            return;
        }
        hasFlag = true;
        hasQuestion = false;
    }

    public void setQuestion(boolean enable) {
        if (openState) {
            return;
        }
        hasQuestion = true;
        hasFlag = false;
    }

    public int toInt() {
        if (!openState) {
            return MineSweeper.NOT_OPEN;
        }
        if (hasFlag) {
            return MineSweeper.FLAG;
        }
        if (hasQuestion) {
            return MineSweeper.QUESTION;
        }
        if (explode) {
            return MineSweeper.CRASH;
        }
        if (boom) {
            return MineSweeper.BOOM;
        }
        return this.number;
    }
}
