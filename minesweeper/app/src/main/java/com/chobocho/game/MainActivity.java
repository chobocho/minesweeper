package com.chobocho.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.chobocho.game.ui.UiManager;
import com.chobocho.game.ui.UiManagerImpl;
import com.chobocho.minesweeper.MineSweeper;
import com.chobocho.minesweeper.MineSweeperImpl;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    MineSweeper mineSweeper;
    MineSweeperView gameView;
    UiManager uiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(gameView);
    }

    protected void init() {
        String version = getVersion();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        ((Display) display).getSize(size);
        int width = size.x;
        int height = size.y;

        BoardProfile boardProfile = new BoardProfile(version, width, height);
        mineSweeper = new MineSweeperImpl();
        uiManager = new UiManagerImpl(this, boardProfile, mineSweeper);
        gameView = new MineSweeperView(this, uiManager);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gameView != null) {
            gameView.resumeGame();
        }
    }
}