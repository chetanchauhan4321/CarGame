package com.example.user.cargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by user on 24-11-2017.
 */

public class EnemyCar2 {

    private Bitmap bitmap;
    private int x,x1,sy,midx;
    private int y,z,y1;

    public EnemyCar2(Context context, int screenx, int screeny){
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.truck2);
        x=screenx+bitmap.getWidth();

        x1=x;
        z=screeny;
        midx=z/2;
        Random r=new Random();
        // y=1+r.nextInt(screeny-bitmap.getWidth()-screeny/2);
        //y=screeny/2-r.nextInt(screeny-bitmap.getWidth());

        y=r.nextInt(screeny/2-bitmap.getWidth());
        sy=screeny;
        //y1=y;


    }

    public void change()
    {
        Random r=new Random();
        int k=5+r.nextInt(30);
        x=x-k;
        if(x<=midx+100)
        {
            y=y-2;
        }
        else if(y<=0)
        {
            y=y+2;
        }
        else y=y+2;

        if(x<=0)
        {
            x=x1;
            Random r2=new Random();
            // y=1+r2.nextInt(z-bitmap.getWidth());
            y=r2.nextInt(sy/2-bitmap.getWidth());

        }
    }

    public Bitmap getBitmap(){ return bitmap;}
    public int getX(){return x;}
    public int getY(){return y;}
}
