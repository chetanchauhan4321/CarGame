package com.example.user.cargame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by user on 23-11-2017.
 */

public class CPlayer {

    Bitmap bp;
     int g;
     int x,x1,y,y1,score=0;
    boolean b;
    CPlayer(Context ct,int screenx,int screeny)
    {
        bp= BitmapFactory.decodeResource(ct.getResources(),R.drawable.car5);
        x=0;
        x1=screenx-bp.getWidth(); // -bp.getWidth
        y=screeny/2-bp.getHeight()/2;
        y1=y;
       // btmy=screeny;
        b=false;
        // gravity=10;
        g=15;

    }
    void moveLeft(){b=false;}
    void moveRight(){
        b=true;
    }

    void change(){
        y=y+g;
        if(b){

            x=x+10;
            y=y-30;
        }

        else{
            x=x+10;
        }

        if(x>=x1){
            x=0;
            //y=y1;

            score=score+5;
        }


    }

    public Bitmap getBp() {
        return bp;
    }

    public void setBp(Bitmap bp) {
        this.bp = bp;
    }
    public int getScore(){return score;}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }




}
