package com.sandip.myword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sandip.util.ASR;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.Util;
import com.sandip.util.UtilPref;

import static java.security.AccessController.getContext;

public class ActivityWelcomeScreen extends Activity implements View.OnClickListener {


    AnimatorSet sunAnimatorSet;
    AnimatorSet cloud1AnimatorSet;

    ValueAnimator skyAnimator;
    ValueAnimator groundAnimator;

    Button btnMornig, btnAfternoon, btnEvening, btnPlay, btnSettings, btnHelp;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_welcome_play:
                Intent i = new Intent(ActivityWelcomeScreen.this, ActivityChapterScreen.class);
                startActivity(i);
                break;
            case R.id.btn_welcome_help:
                Intent i1 = new Intent(ActivityWelcomeScreen.this, ActivityHelp.class);
                startActivity(i1);
                break;
            case R.id.btn_welcome_setings:
                Intent i2 = new Intent(ActivityWelcomeScreen.this, ActivitySettingScreen.class);
                startActivity(i2);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        setUI();
        // ...
    }

    private void setUI() {
        btnMornig = (Button) findViewById(R.id.btnMorning);
        btnMornig.setOnClickListener(this);

        btnAfternoon = (Button) findViewById(R.id.btnAfternoon);
        btnAfternoon.setOnClickListener(this);

        btnEvening = (Button) findViewById(R.id.btnEvening);
        btnEvening.setOnClickListener(this);

        btnPlay = (Button) findViewById(R.id.btn_welcome_play);
        btnPlay.setOnClickListener(this);

        btnSettings = (Button) findViewById(R.id.btn_welcome_setings);
        btnSettings.setOnClickListener(this);

        btnHelp = (Button) findViewById(R.id.btn_welcome_help);
        btnHelp.setOnClickListener(this);
        int timeOfDay = Util.getTime();
        if (timeOfDay >= 0 && timeOfDay < 12) {
            if (!UtilPref.getFromPrefs(this, "fromSettings", "").equalsIgnoreCase("true")) {
                ASR.getInstance(ActivityWelcomeScreen.this).textToSpeech(ActivityWelcomeScreen.this, "Good Moooorniiiiing,      Welcome in the My word");
            }
            morning();
            skyAnimator.start();
            //groundAnimator.start();
            cloud1AnimatorSet.start();
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            if (!UtilPref.getFromPrefs(this, "fromSettings", "").equalsIgnoreCase("true")) {
                ASR.getInstance(ActivityWelcomeScreen.this).textToSpeech(ActivityWelcomeScreen.this, "Good Afternoon,      Welcome in the My word");
            }
            afternoon();
            sunAnimatorSet.start();
            skyAnimator.start();

        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            if (!UtilPref.getFromPrefs(this, "fromSettings", "").equalsIgnoreCase("true")) {
                ASR.getInstance(ActivityWelcomeScreen.this).textToSpeech(ActivityWelcomeScreen.this, "Good Evening,      Welcome in the My word");
            }
            evening();
            sunAnimatorSet.start();
            skyAnimator.start();
        } else if (timeOfDay >= 21 && timeOfDay < 24) {

            night();
            if (!UtilPref.getFromPrefs(this, "fromSettings", "").equalsIgnoreCase("true")) {
                ASR.getInstance(ActivityWelcomeScreen.this).textToSpeech(ActivityWelcomeScreen.this, "Have a Good Night,      Welcome in the My word");
            }
        }

    }

    private void night() {
        ImageView sun = (ImageView) findViewById(R.id.sun);
        sun.setVisibility(View.GONE);

        RelativeLayout sky = (RelativeLayout) findViewById(R.id.sky);
        RelativeLayout nightCloud = (RelativeLayout) findViewById(R.id.rel_night_cloud);
        sky.setVisibility(View.VISIBLE);
        nightCloud.setVisibility(View.VISIBLE);

        ImageView cloud1 = (ImageView) findViewById(R.id.cloud1);
        cloud1.setVisibility(View.GONE);

        // cloud2
        ImageView cloud2 = (ImageView) findViewById(R.id.cloud2);
        cloud2.setVisibility(View.GONE);


        sky.setBackgroundColor(Color.rgb(0x00, 0x00, 0x4c));

    }

    private void morning() {
       /* sunAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.sun_rise);*/
        ImageView sun = (ImageView) findViewById(R.id.sun);
       /* Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.slideup);*/
        Animation animation = new TranslateAnimation(0, 0, 100, 0);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        sun.startAnimation(animation);

        // cloud1
        RelativeLayout cloud1 = (RelativeLayout) findViewById(R.id.rel_bird);
        cloud1AnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.cloud_movement);
        cloud1AnimatorSet.setTarget(cloud1);


        // ...

        skyAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.sky), "backgroundColor",
                        Color.rgb(0x00, 0x00, 0x4c), Color.rgb(0xae, 0xc2, 0xff));
        //set same duration and animation properties as others
        skyAnimator.setDuration(10000);
        skyAnimator.setEvaluator(new ArgbEvaluator());
        // skyAnimator.setRepeatCount(ValueAnimator.RESTART);
        //   skyAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // ...

        // ...

        skyAnimator.addUpdateListener(

                new ValueAnimator.AnimatorUpdateListener() {

                    TextView textView = (TextView) findViewById(R.id.textView);
                    float animatedFractionPrev = 0.0f;
                    float animatedFractionCurr = 0.0f;

                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        animatedFractionCurr = valueAnimator.getAnimatedFraction();
                        if (animatedFractionCurr > animatedFractionPrev) {
                            if (animatedFractionCurr > 0.0 && animatedFractionCurr <= 0.50) {
                                textView.setText("Good morning!");
                            } else {
                                textView.setText("Good day!");
//                                sunAnimatorSet.cancel();

                            }
                            animatedFractionPrev = animatedFractionCurr;

                        }
                    }
                }
        );

        // ...


        // ...

        groundAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.ground), "backgroundColor",
                        Color.rgb(0x00, 0x47, 0x00), Color.rgb(0x85, 0xae, 0x85));
        //set same duration and animation properties as others
        groundAnimator.setDuration(10000);
        groundAnimator.setEvaluator(new ArgbEvaluator());
        //  groundAnimator.setRepeatCount(ValueAnimator.INFINITE);
        // groundAnimator.setRepeatMode(ValueAnimator.REVERSE);

    }

    private void afternoon() {
        sunAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.sun_afternoon);
        ImageView sun = (ImageView) findViewById(R.id.sun);
        sunAnimatorSet.setTarget(sun);
        sunAnimatorSet.addListener(

                new AnimatorListenerAdapter() {

                    public void onAnimationStart(Animator animation) {

                    }

                    public void onAnimationEnd(Animator animation) {

                        groundAnimator.cancel();
                        skyAnimator.cancel();
                    }

                });
        // ...

        // cloud1
        RelativeLayout cloud1 = (RelativeLayout) findViewById(R.id.rel_bird);
        cloud1AnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.cloud_movement);
        cloud1AnimatorSet.setTarget(cloud1);
        skyAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.sky), "backgroundColor",
                        Color.rgb(0x00, 0x00, 0x4c), Color.rgb(0xae, 0xc2, 0xff));
        //set same duration and animation properties as others
        skyAnimator.setDuration(10000);
        skyAnimator.setEvaluator(new ArgbEvaluator());
        skyAnimator.addUpdateListener(

                new ValueAnimator.AnimatorUpdateListener() {

                    TextView textView = (TextView) findViewById(R.id.textView);
                    float animatedFractionPrev = 0.0f;
                    float animatedFractionCurr = 0.0f;

                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        animatedFractionCurr = valueAnimator.getAnimatedFraction();
                        if (animatedFractionCurr > animatedFractionPrev) {
                            if (animatedFractionCurr >= 0.8) {
                                textView.setText("Good day!");
                                sunAnimatorSet.cancel();
                                skyAnimator.cancel();
                            } else if (animatedFractionCurr < 0.8 && animatedFractionCurr >= 0.1) {
                                textView.setText("Good afternoon!");

                            }
                        }
                        animatedFractionPrev = animatedFractionCurr;
                    }
                }
        );

        // ...


        // ...

        groundAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.ground), "backgroundColor",
                        Color.rgb(0x00, 0x47, 0x00), Color.rgb(0x85, 0xae, 0x85));
        //set same duration and animation properties as others
        groundAnimator.setDuration(10000);
        groundAnimator.setEvaluator(new ArgbEvaluator());

    }

    private void evening() {
        sunAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.sun_evening);
        ImageView sun = (ImageView) findViewById(R.id.sun);
        sunAnimatorSet.setTarget(sun);
        sunAnimatorSet.addListener(

                new AnimatorListenerAdapter() {

                    public void onAnimationStart(Animator animation) {

                    }

                    public void onAnimationEnd(Animator animation) {

                    }

                });
        // ...

        RelativeLayout cloud1 = (RelativeLayout) findViewById(R.id.rel_bird);
        cloud1AnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.cloud_movement);
        cloud1AnimatorSet.setTarget(cloud1);

        skyAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.sky), "backgroundColor",
                        Color.rgb(0x00, 0x00, 0x4c), Color.rgb(0xae, 0xc2, 0xff));
        //set same duration and animation properties as others
        skyAnimator.setDuration(10000);
        skyAnimator.setEvaluator(new ArgbEvaluator());
        skyAnimator.setRepeatCount(ValueAnimator.RESTART);
        skyAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // ...

        // ...

        skyAnimator.addUpdateListener(

                new ValueAnimator.AnimatorUpdateListener() {

                    TextView textView = (TextView) findViewById(R.id.textView);
                    float animatedFractionPrev = 0.0f;
                    float animatedFractionCurr = 0.0f;

                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        animatedFractionCurr = valueAnimator.getAnimatedFraction();
                        if (animatedFractionCurr > animatedFractionPrev) {
                            if (animatedFractionCurr >= 0.8) {
                                textView.setText("Good day!");
                            } else if (animatedFractionCurr < 0.8 && animatedFractionCurr >= 0.1) {
                                textView.setText("Good afternoon!");
                            }
                        }
                        animatedFractionPrev = animatedFractionCurr;
                    }
                }
        );

        groundAnimator = ObjectAnimator.ofInt
                (findViewById(R.id.ground), "backgroundColor",
                        Color.rgb(0x00, 0x47, 0x00), Color.rgb(0x85, 0xae, 0x85));
        //set same duration and animation properties as others
        groundAnimator.setDuration(10000);
        groundAnimator.setEvaluator(new ArgbEvaluator());

    }


    @Override
    public void onResume() {
        super.onResume();
    }


}
