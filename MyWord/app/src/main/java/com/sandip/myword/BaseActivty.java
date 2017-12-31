package com.sandip.myword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sandip.util.ASR;
import com.sandip.util.BackgroundSoundService;

public class BaseActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent svc=new Intent(this, BackgroundSoundService.class);
        ASR.getInstance(this).textToSpeech(this,"");
        startService(svc);
    }

    @Override
    protected void onPause() {
        super.onPause();
       if(BackgroundSoundService.player.isPlaying()) {
            BackgroundSoundService.player.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BackgroundSoundService.player.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(BackgroundSoundService.player.isPlaying()) {
            BackgroundSoundService.player.stop();
            BackgroundSoundService.player.release();
        }
    }
}
