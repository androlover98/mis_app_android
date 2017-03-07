package com.android.mis.javac;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.mis.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity {

    Thread mSplashThread;
    Animation fadeIn;
    final SplashScreen sPlashScreen = this;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = (ImageView)findViewById(R.id.logo);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        mSplashThread = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        // Wait given period of time or exit on touch
                        logo.startAnimation(fadeIn);
                        wait(3000);
                    }
                }
                catch(InterruptedException ex){
                }

                finish();

                // Run next activity
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        mSplashThread.start();
    }
}
