package com.chobocho.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
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

        // API 30 이상에서 decorView가 attach된 이후에 시스템 UI 숨김 실행
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        }

        init();
        setContentView(gameView);
        if (mineSweeper != null) {
           loadGameState();
        }
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // DecorView가 attach된 후에 실행하도록 post() 사용
            getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    WindowInsetsController insetsController = getWindow().getInsetsController();
                    if (insetsController != null) {
                        insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                        insetsController.setSystemBarsBehavior(
                                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                        );
                    }
                }
            });
        } else {
            @SuppressWarnings("deprecation")
            int flags = View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            getWindow().getDecorView().setSystemUiVisibility(flags);
        }
    }

    protected void init() {
        String version = getVersion();

        int safeWidth;
        int safeHeight;

        WindowManager windowManager = getWindowManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = windowManager.getCurrentWindowMetrics();
            Rect bounds = windowMetrics.getBounds();
            WindowInsets insets = windowMetrics.getWindowInsets();
            Insets systemInsets = insets.getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout()
            );
            safeWidth = bounds.width() - systemInsets.left - systemInsets.right;
            safeHeight = bounds.height() - systemInsets.top - systemInsets.bottom;
        } else {
            // API 29 이하일 때: 기존 방식 사용
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            safeWidth = size.x;
            safeHeight = size.y;
        }

        BoardProfile boardProfile = new BoardProfile(version, safeWidth, safeHeight);
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