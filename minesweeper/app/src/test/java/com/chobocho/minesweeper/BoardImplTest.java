package com.chobocho.minesweeper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardImplTest {
    BoardImpl testBoard;
    int boardWidth = 10;
    int boardHeight = 10;
    int boomCount = 10;

    @Before
    public void setUp() throws Exception {
        boardWidth = 10;
        boardHeight = 10;
        boomCount = 10;
        testBoard = new BoardImpl(boardWidth, boardHeight, boomCount);
    }

    @Test
    public void check_generate_boom() {
        testBoard.init();

        int boom = 0;

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                testBoard.open(i, j);
            }
        }
        int [][]board = testBoard.getBoard();

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (board[j][i] == MineSweeper.CRASH) {
                    boom++;
                }
            }
        }
        assertEquals(boomCount, boom);
    }

    @Test
    public void isOpen() {
        testBoard.init();
        List<Integer> opened_points = new ArrayList<>();
        int open_block_count = 0;

        testBoard.open(0, 0);
        testBoard.open(1, 1);

        int [][]board = testBoard.getBoard();

        int boom = 0;
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (board[j][i] != MineSweeper.NOT_OPEN) {
                    opened_points.add(j*boardWidth + i);
                    //System.out.println(j*boardWidth+i);
                    open_block_count++;
                }
            }
        }

        opened_points.stream().forEach( p -> {
            //System.out.println(p%boardWidth +" , " + p/boardWidth);
            assertEquals(true, testBoard.isOpen(p%boardWidth, p/boardWidth));
        });
    }

    @Test
    public void isBoom() {
        testBoard.init();
        int [][]board = testBoard.getBoard();
        List<Integer> point = new ArrayList<>();

        int boom = 0;
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (board[j][i] == MineSweeper.BOOM) {
                    point.add(j*boardWidth + i);
                    //System.out.println(j*boardWidth+i);
                }
            }
        }

        point.stream().forEach( p -> {
            //System.out.println(p%boardWidth +" , " + p/boardWidth);
            assertEquals(true, testBoard.hasBoom(p%boardWidth, p/boardWidth));
        });

    }

    @Test
    public void isFinish() {

    }
}