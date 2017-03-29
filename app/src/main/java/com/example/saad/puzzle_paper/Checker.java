package com.example.saad.puzzle_paper;

import android.os.Handler;

/**
 * Created by saad on 26/03/17.
 */

public class Checker{

    public Checker(final int firstImage, final int firstpos, final int position, final int secondImage){
        //if the 2 indexes are equal then they've same image
        if (firstImage == secondImage) {
            int score = Integer.parseInt((String) MainActivity.val1.getText()) + 10;
            MainActivity.val1.setText("" + score);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.imageViews[position].setImageResource(android.R.color.transparent);
                    MainActivity.imageViews[firstpos].setImageResource(android.R.color.transparent);
                }
            }, 700);
        } else {
            MainActivity.boolimgs[position] = false;
            MainActivity.boolimgs[firstpos] = false;

            //restore images to cover image
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.imageViews[position].setImageResource(R.drawable.cover);
                    MainActivity.imageViews[firstpos].setImageResource(R.drawable.cover);
                }
            },700);
            MainActivity.val2.setText("" + (Integer.parseInt((String) MainActivity.val2.getText()) + 1));
        }
    }
}