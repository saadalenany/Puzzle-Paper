package com.example.saad.puzzle_paper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saad on 26/03/17.
 */

public class MainActivity extends AppCompatActivity {


    //original images to be displayed
    public static ImageView[] imageViews;

    //random images to be displayed
    public static ArrayList<Integer> randomimages;

    //array of booleans for pressed images
    public static boolean[] boolimgs ;

    boolean SECOND_IMAGE_CLICKED;

    Integer firstImage;
    int firstpos;
    int position;

    public static TextView val1 ;

    Context con = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val1 = (TextView) findViewById(R.id.val1);

        loadData();

        for (int i = 0; i < 16; i++) {
            final int finalI = i;
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = finalI;
                    clickImage(SECOND_IMAGE_CLICKED,position);
                    if(randomimages.size() == 0){
                        showDialog();
                    }
                }
            });
        }

        Log.d("images :", imageViews.length + "");

    }

    public void clickImage(boolean FIRST_IMAGE_CLICKED, int position) {

        if(boolimgs[position]){
            //do nothing with it
        }else {
            if (FIRST_IMAGE_CLICKED) {
                clickSecond();
            } else {
                clickfirst();
            }
        }
    }

    public void clickfirst(){
        Log.d("case ","first image");
        Log.d("current image position ",position+"");

        //shuffle images & choose one
        int rand = (int)(Math.random()*randomimages.size());

        Log.d("random image position ",rand+"");

        imageViews[position].setImageResource(randomimages.get(rand));

        //set the image as pressed
        boolimgs[position] = true;

        //first image index in array
        firstImage =  rand;

        //first image position
        firstpos = position;
        SECOND_IMAGE_CLICKED = true;
    }

    public void clickSecond(){
        Log.d("case ","second image");
        Log.d("current image position ",position+"");
        Log.d("first image position ",firstpos+"");

        //shuffle images & choose one
        int rand = (int)(Math.random()*randomimages.size()) ;

        Log.d("random image position ",rand+"");

        imageViews[position].setImageResource(randomimages.get(rand));

        //set the image as pressed
        boolimgs[position] = true;

        new Checker(firstImage,firstpos,position,rand,con);

        firstImage = 0;
        SECOND_IMAGE_CLICKED = false;
    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        // set title
        alertDialogBuilder.setTitle("Your Score "+val1.getText());

        // set dialog message
        alertDialogBuilder.setMessage("Play another game!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //load all data to activity again
                        loadData();
                        //set all imageViews again
                        setImageViews();
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        MainActivity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void setImageViews(){
        for (int i = 0; i < 16; i++) {
            imageViews[i].setImageResource(R.drawable.cover);
        }

    }

    public void loadData(){
        imageViews = new ImageView[16];
        randomimages = new ArrayList<>();
        boolimgs = new boolean[16];

        randomimages.add(R.drawable.blue);
        randomimages.add(R.drawable.golden);
        randomimages.add(R.drawable.green);
        randomimages.add(R.drawable.greenwithpink);
        randomimages.add(R.drawable.pink);
        randomimages.add(R.drawable.red);
        randomimages.add(R.drawable.rose);
        randomimages.add(R.drawable.white);

        SECOND_IMAGE_CLICKED = false;

        Integer[] imagesids = {R.id.image0,R.id.image1,R.id.image2,R.id.image3,R.id.image4,R.id.image5,R.id.image6,R.id.image7,R.id.image8,R.id.image9,R.id.image10,R.id.image11,R.id.image12,R.id.image13,R.id.image14,R.id.image15};
        for (int i = 0; i < 16; i++) {
            Log.d("imagesids ",imagesids[i]+"");
            ImageView iv = (ImageView) findViewById(imagesids[i]);
            imageViews[i]=iv;
            boolimgs[i]=false;
        }

        val1.setText(0+"");
    }
}