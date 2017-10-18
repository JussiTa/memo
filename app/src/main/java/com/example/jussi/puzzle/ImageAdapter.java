package com.example.jussi.puzzle;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by jussi on 10/8/17.
 */

public class ImageAdapter extends BaseAdapter {


    private final Context mContext;
    private ImageView imageView;
    private Integer[] randomImages=  {R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3,R.drawable.sample_4,
            R.drawable.sample_5,R.drawable.sample_6,R.drawable.sample_7,R.drawable.sample_0};
    private int[] pair = new int[2];
    private Integer[] mThumbIds1 =new Integer[16];
    private Integer[] same= new Integer[2];
    private  playActivity playActivity;
    private  int clicks=0;
    private Integer positionold=null;
    private boolean pairFound = false;
    private Integer empty = R.drawable.empty;


    public  ImageAdapter(Context context){
            this. mContext = context;
        playActivity= (com.example.jussi.puzzle.playActivity) context;
        Random rand = new Random();
        int j=0;
             //Fill table with images
            for(int i=0; i<randomImages.length;i++){
                mThumbIds1[j]=randomImages[i];
                mThumbIds1[j+=1]=randomImages[i];
                j++;
             }

            int temp;
            int randindex;
             //Confuse images in table
            for(int i=0;i<mThumbIds1.length;i++){
                temp=mThumbIds1[i];
                 randindex=rand.nextInt(15-0+1);
                mThumbIds1[i] = mThumbIds1[randindex];
                mThumbIds1[randindex]=temp;

            }
        }


     public ImageAdapter getInstance(){
         return this;
     }


     public boolean pairFound(){



         return this.pairFound;

     }

      //setting randomize picture from another table
     public void setRigtpicture(int position, View convertView){

         imageView = (ImageView) convertView;
         if(!empty.equals(mThumbIds[position])) {

             if (clicks < 1) {
                 clicks++;
                 this.positionold = position;
                 imageView.setImageResource(mThumbIds[position] = mThumbIds1[position]);
                 same[0] = mThumbIds1[position];




             } else if (clicks == 1 && this.positionold != position) {
                 imageView.setImageResource(mThumbIds[position] = mThumbIds1[position]);
                 same[1] = mThumbIds1[position];

                 if (same[0].equals(same[1]))
                     pairFound=true;

                 clicks++;
             }

         }


     }

           //
    public void removePair(int position, View convertView) {

            imageView = (ImageView) convertView;
            mThumbIds[position]=R.drawable.empty;
            mThumbIds[positionold]=R.drawable.empty;
            imageView.setImageResource(mThumbIds[position]);
            imageView.setImageResource(mThumbIds[positionold]);
        //parent.removeViewAt(positionold);




    }
    public int getCount() {
        return mThumbIds.length;
    }

    public int getClicks(){
        return clicks;

    }


    public void resetSame(){
        clicks=0;
        positionold=null;
        pairFound =false;
        same[0]=5000;
        same[1]=5001;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }





    // create a new ImageView for each item referenced by the Adapter

     public View getView(int position,  View convertView, ViewGroup parent) {

        if (convertView == null) {

            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(10, 10, 10, 10);

        } else {

            imageView = (ImageView) convertView;


           // return imageView;


        }

         imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {

            R.drawable.sample_10, R.drawable.sample_10,
            R.drawable.sample_10, R.drawable.sample_10,
            R.drawable.sample_10, R.drawable.sample_10,
            R.drawable.sample_10, R.drawable.sample_10,
            R.drawable.sample_10, R.drawable.sample_10,
            R.drawable.sample_10, R.drawable.sample_10,
            R.drawable.sample_10, R.drawable.sample_10,
            R.drawable.sample_10, R.drawable.sample_10,
    };

    public ImageView setBackground(int position, View convertView) {
        if(!empty.equals(mThumbIds[position])) {

            imageView = (ImageView) convertView;
            imageView.setImageResource(mThumbIds[position] = R.drawable.sample_10);
            clicks++;
        }
        return imageView;
    }

    public boolean checkAllCardAreSameSideUp(int position1, int position2){
        Integer sample10=R.drawable.sample_10;
        if(sample10.equals(mThumbIds[position1]) && sample10.equals(mThumbIds[position2]))
               return true;

     return false ;


    }


    public void setplayActivity(com.example.jussi.puzzle.playActivity playActivity) {
        this.playActivity = playActivity;
    }


    public  View getImageView(){

        return this.imageView;
    }


}
