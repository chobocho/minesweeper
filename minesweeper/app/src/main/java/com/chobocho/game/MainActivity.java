package com.chobocho.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.chobocho.game.input.InputManager;
import com.chobocho.game.input.InputManagerImpl;
import com.chobocho.game.ui.UiManager;
import com.chobocho.game.ui.UiManagerImpl;
import com.chobocho.minesweeper.GameInfo;
import com.chobocho.minesweeper.GameObserver;
import com.chobocho.minesweeper.MineSweeper;
import com.chobocho.minesweeper.MineSweeperImpl;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    MineSweeper mineSweeper;
    MineSweeperView gameView;
    UiManager uiManager;
    InputManager inputManger;

    int minesweeper_width = 10;
    int minesweeper_height = 1;
    int boomCount = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        init();
        setContentView(gameView);
        if (mineSweeper != null) {
           loadGameState();
        }
    }

    protected void init() {
        String version = getVersion();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        ((Display) display).getSize(size);
        int width = size.x;
        int height = size.y;

        BoardProfile boardProfile = new BoardProfile(version, width, height);
        mineSweeper = new MineSweeperImpl(10, 10, boomCount);
        uiManager = new UiManagerImpl(this, boardProfile, mineSweeper);
        inputManger = new InputManagerImpl(boardProfile, mineSweeper);
        gameView = new MineSweeperView(this, mineSweeper, uiManager, inputManger);
    }

    private String getVersion() {
        try {
            PackageInfo pkgInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pkgInfo.versionName;
            Log.i(TAG, "Version Name : "+ version);
            return version;
        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, e.toString());
        }
        return "";
    }

    @Override
    public void onPause() {
        super.onPause();
        if (gameView != null) {
            gameView.pauseGame();
        }
        if (mineSweeper != null) {
            Log.d(TAG, "Game is Paused!");
            if (mineSweeper.isPlayState()) {
                mineSweeper.pause();
            }
            GameInfo gameInfo = mineSweeper.getGameInfo();
            saveGameState(gameInfo);
        }
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();
        if (gameView != null) {
            Log.d(TAG, "Game is Resumed!");
            gameView.resumeGame();
        }
    }

    private void saveGameState(GameInfo gi) {
        Log.i(TAG, "saveGameState()");

        if (gi == null) {
            return;
        }

        GameInfo gameInfo = gi;

        SharedPreferences pref = this.getSharedPreferences("MineSweeper", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        edit.putInt("gameState", gameInfo.getGameState());
        edit.putInt("playTime", gameInfo.getPlayTime());
        edit.putInt("unOpenedTileCount", gameInfo.getUnopenedTileCount());
        edit.putInt("unUsedFlagCount", gameInfo.getUnusedFlagCount());
        edit.putString("board", gameInfo.getBoard());
        edit.commit();
    }

    private void loadGameState(){
        Log.i(TAG, "loadGameState()");

        SharedPreferences pref = this.getSharedPreferences("MineSweeper", MODE_PRIVATE);
        GameInfo gameInfo = new GameInfo(minesweeper_width, minesweeper_height);

        int gameState = pref.getInt("gameState", GameObserver.IDLE);
        if (gameState == GameObserver.IDLE) {
            return;
        }

        gameInfo.setGameSate(gameState);
        gameInfo.setPlayTime(pref.getInt("playTime", 0));
        gameInfo.setUnusedFlagCount(pref.getInt("unUsedFlagCount", boomCount));
        gameInfo.setUnopenedTileCount(pref.getInt("unOpenedTileCount", minesweeper_width * minesweeper_height));
        String board = pref.getString("board", "");
        if (board.length() == 0) {
            return;
        }

        gameInfo.setBoard(board);
        if (mineSweeper != null) {
            mineSweeper.setGameInfo(gameInfo);
        }
    }
}