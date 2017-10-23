package com.example.jussi.puzzle;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.jussi.puzzle.R.color.colorPrimary;

/**
 * Created by jussi on 10/16/17.
 */

public class MyGridView extends GridLayout {

    //private  Drawable[] mThumbIds1 = new Drawable[16];
    Resources res = getResources();
    Drawable drawable = res.getDrawable(R.drawable.sample_10);

    private Integer[] randomImages = {R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
            R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0};

    private Integer[] mThumbIds1 = new Integer[16];

    private Map<Integer, Integer> id = new HashMap<Integer, Integer>();
    private int clicks = 0;
    private Integer[] pair = new Integer[2];
    private int i = 0;

    private boolean everyCardBackgroundUp = true;
    Thread t;
    private ImageView view1;
    private ImageView view2;
    boolean pairfound = false;
    boolean seconClicked =false;


    public MyGridView(Context context) {
        super(context);


    }


    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        pair[0] = null;
        pair[1] = null;

        Random rand = new Random();
        int j = 0;
        //Fill table with images
        for (int i = 0; i < randomImages.length; i++) {
            mThumbIds1[j] = randomImages[i];
            mThumbIds1[j += 1] = randomImages[i];
            j++;
        }

        int temp;
        int randindex;
        //Confuse images in table
        for (int i = 0; i < mThumbIds1.length; i++) {
            temp = mThumbIds1[i];
            randindex = rand.nextInt(15 - 0 + 1);
            mThumbIds1[i] = mThumbIds1[randindex];
            mThumbIds1[randindex] = temp;

        }


        t = new Thread(new MyRunnable());


    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        ImageView view;

        if (pair[0] != null && pair[1] != null) {
            Log.d("www","www");
            view1 = (ImageView) getChildAt(pair[0]);
            view2 = (ImageView) getChildAt(pair[1]);
            everyCardBackgroundUp = false;
            seconClicked=true;

            if (view1.getDrawable().equals(drawable)
                    && view2.getDrawable().equals(drawable)) {
                Log.d("kortit", "käännetty");
                pair[0] = null;
                pair[1] = null;
                everyCardBackgroundUp = true;
                seconClicked=false;
            }
        }


        if (everyCardBackgroundUp == false && pair[0] != null && pair[1] != null) {
            Log.d("täällä", "ollaan1");
            ImageView view1 = (ImageView) getChildAt(pair[0]);
            ImageView view2 = (ImageView) getChildAt(pair[1]);
            Log.d("view1", String.valueOf(mThumbIds1[pair[0]]));
            Log.d("view2", String.valueOf(mThumbIds1[pair[1]]));


            if (mThumbIds1[pair[0]].equals(mThumbIds1[pair[1]])) {
                Log.d("täällä", "ollaan");
                pairfound = true;
                //view2.performLongClick();


                t.start();
                try {
                    t.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                view1.setImageResource(R.drawable.empty);
                view2.setImageResource(R.drawable.empty);
                pairfound = false;
                pair[0] = null;
                pair[1] = null;
                everyCardBackgroundUp = true;
                seconClicked=false;




            }


        }


        if (clicks == 0) {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                view = (ImageView) getChildAt(i);
                view.setImageDrawable(drawable);
                id.put(view.getId(), i);


            }



        }
        clicks++;
       /* if (pair[1] != null) {
            everyCardBackgroundUp=false;
            pair[0] = null;
            pair[1] = null;
        }*/


    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //    clicks++;
        int count = getChildCount();
        ImageView child;
      //  Log.d("CLICKS", String.valueOf(clicks));


        for (int i = 0; i < count; i++) {

            child = (ImageView) getChildAt(i);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView view = (ImageView) v ;
                    if (pair[0] == null) {
                        Log.d("pair0","==0" );
                        pair[0] = id.get(view.getId());
                        view.setImageResource(mThumbIds1[id.get(view.getId())]);
                        everyCardBackgroundUp = false;

                    } if (pair[1] == null && everyCardBackgroundUp == false && pair[0]!=id.get(view.getId())) {
                        pair[1] = id.get(view.getId());
                        Log.d("toka","kortti");
                        view.setImageResource(mThumbIds1[id.get(view.getId())]);


                    } if (seconClicked && pair[0]==id.get(view.getId()) || seconClicked && pair[1]==id.get(view.getId()) ) {
                        view.setImageDrawable(drawable);
                        Log.d("käännetään", "takaisin");


                    }


                }
            });
        }

        return super.onInterceptTouchEvent(ev);
    }


    private class MyRunnable implements Runnable {
        private MyRunnable() {
            //SystemClock.setCurrentTimeMillis(0);

        }


        @Override
        public void run() {





        }


    }


}
