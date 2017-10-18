package com.example.jussi.puzzle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
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
        setContentView(R.layout.activity_play);
        final GridView gridview =  (GridView) findViewById(R.id.gridview);
        final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.gridview);

       Thread t = new Thread(new MyRunnable());

       t.start();



        imageadapter = (ImageAdapter) gridview.getAdapter();





        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            



       public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                //First click
                if (klicks == 0 && !locked[position]){
                    position1[0] = position;

                }


                    //Checked second click is not same position
                else if (klicks == 1 && position1[0] != position && !locked[position])
                    position1[1] = position;



                //First click
                if (twoclicked < 1 && position1[0] == position) {
                    imageadapter = (ImageAdapter) gridview.getAdapter();
                    if (!locked[position]) {

                        imageadapter.setRigtpicture(position, v);
                        klicks++;
                    }

                }

                //Second card selected
                if (twoclicked == 1 && position1[1] != null) {
                    imageadapter = (ImageAdapter) gridview.getAdapter();


                    if (position1[1] == position) {
                        if (!locked[position]) {
                            playActivity.view=v;

                            imageadapter.setRigtpicture(position, v);
                            klicks++;
                        }
                    }

                    if (imageadapter.pairFound()) {
                        gridview.setSelection(position1[0]);
                        //Prevented reuse pairfound positions;
                        locked[position1[0]] = true;
                        locked[position1[1]] = true;


                        imageadapter.removePair(position1[1], v);




                        klicks=0;
                        twoclicked = 0;
                        imageadapter.resetSame();
                        position1[0] = 10000;
                        position1[1] = 100000;









                    }


                    //If two cards are turned no pairs
                } else if (twoclicked == 2 || twoclicked == 3) {

                    if (position1[0] == position || position1[1] == position) {
                        if(!locked[position]) {
                            imageadapter = (ImageAdapter) gridview.getAdapter();
                            imageadapter.setBackground(position, v);
                            klicks++;

                        }
                    }


                }
                 //Prevent null point exception from adapter class

                twoclicked = imageadapter.getClicks();

                //Turn cards back
                if (klicks == 4 && imageadapter.checkAllCardAreSameSideUp(position1[0], position1[1])) {
                    klicks = 0;
                    twoclicked = 0;
                    imageadapter.resetSame();
                    position1[0] = 10000;
                    position1[1] = 10000;

                }

            }


        });



    }
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first


         Log.d("onresume","enabled");



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


