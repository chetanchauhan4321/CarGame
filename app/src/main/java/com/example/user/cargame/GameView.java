package com.example.user.cargame;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import static android.content.Context.MODE_WORLD_WRITEABLE;

/**
 * Created by user on 23-11-2017.
 */

public class GameView  extends SurfaceView{
    SurfaceHolder sh;
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    CPlayer cp;
    MyThread mt;
    Boom boom;
    Rect r,r1,r2;
    EnemyCar ec1;
    EnemyCar2 ec2;
    EnemyTruck et;
    EnemyTruck2 et2;
    int tx,ty;
    int btmy,topy;
    Button b1;
  Context cc;
    @SuppressLint("WrongCall")
    public GameView(Context ct,int x,int y)
    {
        super(ct);
        cc=ct;
        tx=x;
        ty=y;
        cp=new CPlayer(ct,x,y);
        boom =new Boom(ct);
        sh=getHolder();
        btmy=y;
        topy=0;
        ec1=new EnemyCar(ct,x,y);
        ec2=new EnemyCar2(ct,x,y);
        et=new EnemyTruck(ct,x,y);
        et2=new EnemyTruck2(ct,x,y);
        mt = new MyThread(this);
        pref= ct.getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();


        sh.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                mt.isrunning(true);
                mt.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas){
        int score=cp.getScore();
        editor.putInt("k1",score);
        Paint ps=new Paint();
        ps.setColor(Color.BLUE);
        ps.setTextSize(50f);


        int btm=cp.getY();
       canvas.drawColor(Color.WHITE);

        r=new Rect(cp.getX(),cp.getY(),cp.getX()+cp.getBp().getWidth(),cp.getY()+cp.getBp().getHeight());
        canvas.drawText("Score:"+score,10,50,ps);
        canvas.drawBitmap(cp.getBp(),cp.getX(),cp.getY(),null);

       // canvas.drawBitmap(ec1.getBitmap(),ec1.getX(),ec1.getY(),null);
       // canvas.drawBitmap(ec2.getBitmap(), ec2.getX(), ec2.getY(), null);
       if(score<5) {
           canvas.drawBitmap(ec1.getBitmap(),ec1.getX(),ec1.getY(),null);
            canvas.drawBitmap(et.getBitmap(), et.getX(), et.getY(), null);

        }
        else if(score<10){
            canvas.drawBitmap(ec2.getBitmap(), ec2.getX(), ec2.getY(), null);
           canvas.drawBitmap(et.getBitmap(),et.getX(),et.getY(),null);
        }
        else if(score<15)
       {
           canvas.drawBitmap(ec1.getBitmap(), ec1.getX(), ec1.getY(), null);
           canvas.drawBitmap(et2.getBitmap(), et2.getX(), et2.getY(), null);


       }
        else {
           canvas.drawBitmap(ec2.getBitmap(), ec2.getX(), ec2.getY(), null);
           canvas.drawBitmap(et2.getBitmap(), et2.getX(), et2.getY(), null);
       }

       // r2 = new Rect(ec2.getX(), ec2.getY(), ec2.getX() + ec2.getBitmap().getWidth(), ec2.getY() + ec2.getBitmap().getHeight());

        if(score<5) {
            r1=new Rect(ec1.getX(),ec1.getY(),ec1.getX()+ec1.getBitmap().getWidth(),ec1.getY()+ec1.getBitmap().getHeight());

            r2 = new Rect(et.getX(), et.getY(), et.getX() + et.getBitmap().getWidth(), et.getY() + et.getBitmap().getHeight());
        }
        else if(score<10)
        {
            r1=new Rect(ec2.getX(),ec2.getY(),ec2.getX()+ec2.getBitmap().getWidth(),ec2.getY()+ec2.getBitmap().getHeight());

            r2 = new Rect(et.getX(), et.getY(), et.getX() + et.getBitmap().getWidth(), et.getY() + et.getBitmap().getHeight());
        }
        else if(score<15)
        {
            r1=new Rect(ec1.getX(),ec1.getY(),ec1.getX()+ec1.getBitmap().getWidth(),ec1.getY()+ec1.getBitmap().getHeight());

            r2 = new Rect(et2.getX(), et2.getY(), et2.getX() + et2.getBitmap().getWidth(), et2.getY() + et2.getBitmap().getHeight());

        }
         else {
            r1=new Rect(ec2.getX(),ec2.getY(),ec2.getX()+ec2.getBitmap().getWidth(),ec2.getY()+ec2.getBitmap().getHeight());

            r2 = new Rect(et2.getX(), et2.getY(), et2.getX() + et2.getBitmap().getWidth(), et2.getY() + et2.getBitmap().getHeight());
        }

         if(Rect.intersects(r,r1)|| Rect.intersects(r,r2))
            {
                if(score>pref.getInt("k1",0)){
                    editor.putInt("k1",score);
                }
            boom.setX(cp.getX()+cp.getBp().getWidth()-150);
            boom.setY(cp.getY()-45);


            canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),null);
            mt.isrunning(false);
            Paint p=new Paint();
            Paint p1=new Paint();
                Paint p2=new Paint();
            p.setColor(Color.RED);
            p1.setColor(Color.BLUE);
                p2.setColor(Color.BLUE);
            p.setTextSize(100f);
            p1.setTextSize(50f);
                p2.setTextSize(50f);

            canvas.drawText("Game Over",350,350,p);
            canvas.drawText("Score:"+score,520,400,p1);
             //   canvas.drawText("Max. Score:"+pref.getInt("k1",0),600,500,p2);
        }

        else if(btm>=btmy-150)
        {
            if(score>pref.getInt("k1",0)){
                editor.putInt("k1",score);
            }

            boom.setX(cp.getX());
            boom.setY(cp.getY()+20);
            canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),null);
            mt.isrunning(false);

            Paint p=new Paint();
            Paint p1=new Paint();
            p.setColor(Color.RED);
            p1.setColor(Color.BLUE);
            p.setTextSize(100f);
            p1.setTextSize(50f);

            canvas.drawText("Game Over",350,350,p);
            canvas.drawText("Score"+score,520,400,p1);



        }
        else if(btm<=topy)
        {
            if(score>pref.getInt("k1",0)){
                editor.putInt("k1",score);
            }
            boom.setX(cp.getX()+20);
            boom.setY(cp.getY()-80);
            canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),null);
            mt.isrunning(false);

            Paint p=new Paint();
            Paint p1=new Paint();
            p.setColor(Color.RED);
            p1.setColor(Color.BLUE);
            p.setTextSize(100f);
            p1.setTextSize(50f);

            canvas.drawText("Game Over",350,350,p);
            canvas.drawText("Score"+score,520,400,p1);





        }

        ec1.change();

        ec2.change();
        et.change();
        et2.change();


        cp.change();

    }
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch(motionEvent.getAction() & motionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                cp.moveLeft();
                break;
            case MotionEvent.ACTION_DOWN:
                cp.moveRight();
                 //cp.setY(cp.getY()-40);
                break;

        }
        return true;
    }


}
