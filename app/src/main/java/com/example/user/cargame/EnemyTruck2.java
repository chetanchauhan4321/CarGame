package com.example.user.cargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by user on 26-11-2017.
 */

public class EnemyTruck2 {
    private Bitmap bitmap;
    private int x,x1,xmid;
    private int y,z,y1;
    private int pwidth;
    EnemyCar enemyCar;
    CPlayer cPlayer;

    public EnemyTruck2(Context context, int screenx, int screeny){
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.bus);
        x=screenx+bitmap.getWidth();
        xmid=screenx/2;
        x1=x;
        z=screeny;
        Random r=new Random();
        y=screeny/2+r.nextInt(screeny-getBitmap().getWidth()-270);
        //  cPlayer=new CPlayer(context,screenx,screeny);
        // enemyCar=new EnemyCar(context,screenx,screeny);
        // pwidth=cPlayer.getBp().getWidth();
        //y=enemyCar.getY()+pwidth+50;
        // y1=y;
    }

    public void change()
    {
        Random r=new Random();
        int k=5+r.nextInt(30);
        x = x - k;
        if(x<=xmid+100) {

            y = y + 2;
        }

        else if(y<=z-180)
        {
            y=y-2;
        }
        else y=y-2;

        if(x<=0)
        {
            x=x1;
            Random r2=new Random();
            y=z/2+r2.nextInt(z-bitmap.getWidth()-270);
            //y=y1;

        }
    }

    public Bitmap getBitmap(){ return bitmap;}
    public int getX(){return x;}
    public int getY(){return y;}
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }


}
