package com.example.user.cargame;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    GameView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        DisplayMetrics dm=getResources().getDisplayMetrics();
        gv=new GameView(this,dm.widthPixels,dm.heightPixels);


    }
    public void dothis(View v){ setContentView(gv);


    }
}
