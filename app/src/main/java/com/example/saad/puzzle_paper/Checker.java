package com.example.saad.puzzle_paper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;

import java.util.TimerTask;

/**
 * Created by saad on 26/03/17.
 */

public class Checker{

    Context con;

    public Checker(final Integer firstImage, final int firstpos, final int position, final int rand, Context con) {
        this.con = con;
        //if the 2 indexes are equal then they've same image
        if (firstImage == rand) {

            int score = 0;
            //if it's a golden image
            if(firstImage == 1){
                score = Integer.parseInt((String) MainActivity.val1.getText()) + 50;
                Toast.makeText(con,"WOW it's a golden dice!",Toast.LENGTH_SHORT).show();
            }else{
                score = Integer.parseInt((String) MainActivity.val1.getText()) + 10;
            }
            MainActivity.val1.setText("" + score);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.imageViews[position].setImageResource(android.R.color.transparent);
                    MainActivity.imageViews[firstpos].setImageResource(android.R.color.transparent);
                    MainActivity.randomimages.remove(rand);
                }
            }, 1000);
        } else {

            if(Integer.parseInt((String) MainActivity.val1.getText()) <=0){
                //keep it at 0
            }else {
                int score = Integer.parseInt((String) MainActivity.val1.getText()) - 10;
                MainActivity.val1.setText("" + score);
            }

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
        }
    }
}