package com.example.jussi.puzzle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.icu.util.TimeZone;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.Calendar;


public class playActivity extends AppCompatActivity {

    private ImageAdapter imageadapter;
    private int twoclicked = 0;
    private Integer[] position1 = new Integer[2];
    private int klicks = 0;
    Calendar calendar = Calendar.getInstance();
    int i = 0;
    private Boolean[] locked = {false, false, false, false, false, false, false, false, false, false, false
            , false, false, false, false, false};


    static View view;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);




      Thread t = new Thread(new MyRunnable());

     t.start();
    }

        private class MyRunnable implements Runnable {
        private MyRunnable() {
            //SystemClock.setCurrentTimeMillis(0);

        }


        @Override
        public void run() {
            while (!Thread.interrupted()) {
                //duration.getSeconds();
                SystemClock.sleep(1000);
                i++;
                //   Log.d("aika sekunteina", String.valueOf(SystemClock.uptimeMillis()));

               // Log.d("aika sekunteina", String.valueOf(i));
                //Log.d("aika sekunteina", String.valueOf( duration.getSeconds()));


            }


        }


    }





}


