package com.chobocho.minesweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    Tile tile;

    @Before
    public void setUp() throws Exception {
        tile = new Tile();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void flagTest() {
        char flag = 0x45;
        tile.setTile(flag);

        assertEquals(true, tile.hasFlag());

        tile.setClear();
        assertEquals(false, tile.hasFlag());
        assertEquals(MineSweeper.NOT_OPEN, tile.toInt());
        assertEquals(5, tile.toChar());

        tile.setFlag(true);
        assertEquals(true, tile.hasFlag());
        assertEquals(0x45, tile.toChar());

        tile.setOpen();
        assertEquals(false, tile.hasFlag());
        assertEquals(5, tile.toInt());
        assertEquals(0x85, tile.toChar());
    }

    @Test
    public void boomTest() {
        char boom = 0x10;
        tile.setTile(boom);
        assertEquals(false, tile.hasFlag());
        assertEquals(true, tile.hasBoom());

        tile.setFlag(true);
        assertEquals(false, tile.hasFlag());
        assertEquals(true, tile.hasBoom());

        tile.setOpen();
        assertEquals(true, tile.hasBoom());
        assertEquals(true, tile.isExploded());

        tile.init();
        assertEquals(false, tile.isOpen());
        assertEquals(false, tile.hasBoom());
        assertEquals(false, tile.isExploded());
        assertEquals(false, tile.hasFlag());
    }

    @Test
    public void initTest() {
        char result = 0;
        assertEquals(result, tile.toChar());
        assertEquals(false, tile.isOpen());
        assertEquals(false, tile.hasBoom());
        assertEquals(false, tile.isExploded());
        assertEquals(false, tile.hasFlag());
        assertEquals(MineSweeper.NOT_OPEN, tile.toInt());

        char open_3 = 0x3;
        tile.setTile(open_3);
        assertEquals(false, tile.isOpen());
        assertEquals(MineSweeper.NOT_OPEN, tile.toInt());
        tile.setOpen();
        assertEquals(3, tile.toInt());
    }
}