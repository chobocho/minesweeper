package com.chobocho.minesweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MineSweeperImplTest {
    MineSweeperImpl mineSweeper = null;

    @Before
    public void setUp() throws Exception {
        int boardWidth = 10;
        int boardHeight = 10;
        int boomCount = 10;
        mineSweeper = new MineSweeperImpl(boardWidth, boardHeight, boomCount);
    }

    @After
    public void tearDown() throws Exception {
        mineSweeper = null;
    }

    @Test
    public void register() {
    }

    @Test
    public void touch() {
    }

    @Test
    public void tick() {
    }

    @Test
    public void play() {
    }

    @Test
    public void pause() {
    }

    @Test
    public void init() {
    }
}