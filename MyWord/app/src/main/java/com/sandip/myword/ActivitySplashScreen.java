package com.sandip.myword;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


import com.sandip.model.ModelChapter;
import com.sandip.util.ASR;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.UtilPref;

import java.util.ArrayList;

public class ActivitySplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    String TAG="ActivitySplashScreen";
    ArrayList<ModelChapter> listOfChapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ASR.getInstance(this).textToSpeech(this,"");
        UtilPref.saveToPrefs(this,"fromSettings","");

        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(ActivitySplashScreen.this, ActivityWelcomeScreen.class);
                startActivity(i);
                // close this activity
                finish();
                
            }
        }, SPLASH_TIME_OUT);
        }
}
