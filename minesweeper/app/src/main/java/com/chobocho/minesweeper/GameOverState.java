package com.chobocho.minesweeper;

/**
 * @author chobocho
 */
public class GameOverState extends State {

    /**
     * Default constructor
     */
    public GameOverState() {
    }
    public int getState() {
        return GameObserver.GAME_OVER;
    }
}