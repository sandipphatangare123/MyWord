package com.sandip.customdialog;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.sandip.myword.R;
import com.sandip.util.UtilPref;


public class DialogFailed extends AppCompatActivity implements View.OnClickListener {
String TAG="DialogSuccess";


   Button btnShow,btnRetry;
String words,wordUse,wordENGMeaning,wordMarathiMeaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_failed);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final MediaPlayer player = MediaPlayer.create(this, R.raw.incorrect_answer);
        player.setLooping(false); // Set looping
        player.setVolume(100,100);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.stop();
                player.release();
            }
        });
        if (bundle != null) {
            words = bundle.getString("Words");
            wordUse = bundle.getString("WordsUse");
            wordENGMeaning = bundle.getString("WordsEngMeaning");
            wordMarathiMeaning = bundle.getString("WordsMarathiMeaning");
            Log.e("DialogSuccess", "Words ==:" + words);
            Log.e("DialogSuccess", "WordsUse ==:" + wordUse);
            Log.e("DialogSuccess", "WordsEngMeaning ==:" + wordENGMeaning);
            Log.e("DialogSuccess", "WordsMarathiMeaning ==:" + wordMarathiMeaning);
        }
        setUI();


    }

    public void setUI()
    {
        btnShow=(Button)findViewById(R.id.btn_show);
        btnRetry=(Button)findViewById(R.id.btn_retry);

        btnShow.setOnClickListener(this);
        btnRetry.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_retry:
                intent=new Intent();
                setResult(1);
                finish();
                break;
            case R.id.btn_show:
                UtilPref.saveToPrefs(this,"from","false");
                intent = new Intent(DialogFailed.this, DialogSuccess.class);
                intent.putExtra("Words", words);
                intent.putExtra("WordsUse",wordUse );
                intent.putExtra("WordsEngMeaning",wordENGMeaning );
                intent.putExtra("WordsMarathiMeaning",wordMarathiMeaning);
                intent.putExtra("Flag","false");
                startActivityForResult(intent,0);
                finish();
                break;
        }
    }
}