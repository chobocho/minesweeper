package com.chobocho.game;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chobocho.game.input.InputManager;
import com.chobocho.game.ui.UiManager;
import com.chobocho.minesweeper.MineSweeper;


public class MineSweeperView extends View {
    private String LOG_TAG = this.getClass().getName();
    private Context mContext;
    private MineSweeper mineSweeper;
    private UiManager uiManager;
    private InputManager inputManager;
    private final ViewListener listener = new ViewListener();

    private HandlerThread playerHandlerThread;
    private Handler playerHandler;

    private static final int EXPIRED_TIMER = 1000;
    private static final int PRESS_KEY = 1001;
    private static final int UPDATE_SCREEN = 1002;

    private int gameSpeed = 1000;

    public MineSweeperView(Context context, MineSweeper mineSweeper, UiManager uiManagerImpl, InputManager inputManagerImpl) {
        super(context);
        this.mContext = context;
        this.mineSweeper = mineSweeper;
        this.uiManager = uiManagerImpl;
        this.uiManager.setListener(listener);
        this.inputManager = inputManagerImpl;
        createPlayerThread();
        startGame();
    }


    public void onDraw(Canvas canvas) {
       this.uiManager.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LOG_TAG, ">> X: " + event.getX() + " Y: " + event.getY());

        if (playerHandler == null) {
            return false;
        }

        if (MotionEvent.ACTION_DOWN != event.getAction()) {
            return false;
        }

        int x = (int) (event.getX());
        int y = (int) (event.getY());

        Message msg = new Message();
        msg.what = PRESS_KEY;
        msg.arg1 = x;
        msg.arg2 = y;
        playerHandler.sendMessage(msg);

        return true;
    }

    public void onTouchReleased(int mouseX, int mouseY) {
        Log.i(LOG_TAG, "Mouse released " + mouseX + ":" + mouseY);
    }

    public void startGame() {
        playerHandler.sendEmptyMessage(EXPIRED_TIMER);
    }

    public void pauseGame() {
        Log.d(LOG_TAG, "pauseGame");
        if (playerHandler.hasMessages(EXPIRED_TIMER)) {
            playerHandler.removeMessages(EXPIRED_TIMER);
            Log.d(LOG_TAG, "Removed event");
        }
        if (playerHandlerThread != null) {
            playerHandlerThread.quit();
        }
    }

    public void resumeGame() {
        Log.d(LOG_TAG, "resumeGame");
        createPlayerThread();
        startGame();
    }

    public void update() {
        Log.d(LOG_TAG, "View.update()");
        invalidate();
    }

    private void createPlayerThread() {
        Log.d(LOG_TAG, "createPlayerThread");
        playerHandlerThread = new HandlerThread("Player Processing Thread");
        playerHandlerThread.start();
        playerHandler = new Handler(playerHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(LOG_TAG, "There is event : " + msg.what);

                switch (msg.what) {
                    case PRESS_KEY:
                        inputManager.onTouch(msg.arg1, msg.arg2);
                        playerHandler.sendEmptyMessageDelayed(UPDATE_SCREEN, 50);
                        break;
                    case EXPIRED_TIMER:
                        if (mineSweeper != null && mineSweeper.isPlayState()) {
                            mineSweeper.tick();
                        } else {
                            // without this code crash happened!
                            break;
                        }
                        if (playerHandler.hasMessages(EXPIRED_TIMER)) {
                            playerHandler.removeMessages(EXPIRED_TIMER);
                        }
                        playerHandler.sendEmptyMessageDelayed(EXPIRED_TIMER, gameSpeed);
                        invalidate();
                        break;
                    case UPDATE_SCREEN:
                        if (playerHandler.hasMessages(UPDATE_SCREEN)) {
                            playerHandler.removeMessages(UPDATE_SCREEN);
                        }
                        invalidate();
                        break;
                }
            }
        };
    }

    public class ViewListener {
        ViewListener() {

        }
        public void update() {
            Message message = new Message();
            message.what = EXPIRED_TIMER;
            playerHandler.sendMessage(message);
        }
    }
}
