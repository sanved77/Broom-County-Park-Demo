package com.sanved.parkdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Sanved on 12-07-2018.
 */

public class LaunchScreen extends AppCompatActivity {

    FloatingActionButton show;
    LinearLayout tvtest;

    private final static int IMAGEVIEW = 1;
    private final static int FABBUTTON = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);

        show = findViewById(R.id.fabDone);

        tvtest = findViewById(R.id.tvtest);

        tvtest.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                setAnim(IMAGEVIEW);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAnim();
                Intent i = new Intent(LaunchScreen.this, StartScreen.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void setAnim(final int ID){

        View myview = tvtest;
        switch(ID){
            case IMAGEVIEW:
                myview = tvtest;
                break;
            case FABBUTTON:
                myview = show;
                break;
            default:
                Toast.makeText(this, "Error in setAnim, set the ID neatly", Toast.LENGTH_SHORT).show();
                break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = myview.getWidth() / 2;
            int cy = myview.getHeight() / 2;
            float finalRadius = (float) Math.hypot(cx, cy);
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(myview, cx, cy, 0, finalRadius);
            myview.setVisibility(View.VISIBLE);
            anim.setDuration(1200);
            anim.start();
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if(ID == IMAGEVIEW) {
                        show.setVisibility(View.VISIBLE);
                        show.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                            @Override
                            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                                setAnim(FABBUTTON);
                            }
                        });
                    }

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        } else {
            tvtest.setVisibility(View.VISIBLE);
        }
    }

    public void resetAnim(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = tvtest.getWidth() / 2;
            int cy = tvtest.getHeight() / 2;
            float initialRadius = (float) Math.hypot(cx, cy);
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(tvtest, cx, cy, initialRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    tvtest.setVisibility(View.INVISIBLE);
                }
            });
            anim.start();
        } else {
            tvtest.setVisibility(View.INVISIBLE);
        }
    }
}
