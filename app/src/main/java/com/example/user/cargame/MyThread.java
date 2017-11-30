package com.example.user.cargame;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by user on 23-11-2017.
 */

public class MyThread extends Thread{
    boolean flag=false;
    GameView gameView;

    public MyThread(GameView gv)
    {
        gameView=gv;

    }

    void isrunning(boolean flag)
    {
        this.flag=flag;
    }
    @SuppressLint("WrongCall")
    @Override
    public void run() {
        while (flag){
            Canvas c=null;
            try {
                Thread.sleep(100);
                synchronized (gameView.getHolder()){
                    c = gameView.getHolder().lockCanvas();
                    gameView.onDraw(c);
                }
            } catch (Exception e) {
            }
            finally {
                gameView.getHolder().unlockCanvasAndPost(c);
            }
        }
    }
}
