package com.sandip.customdialog;

import android.content.Intent;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandip.myword.ActivityPlayBoard;
import com.sandip.myword.R;
import com.sandip.util.ASR;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.UtilPref;


public class DialogSuccess extends AppCompatActivity implements View.OnClickListener {
    String TAG = "DialogSuccess";
    //false
    TextView txtWord, txtWordEngMeaning, txtWordMarathiMeaning, txtUseLink, txtWordUse, txtTitle, title;
    Button btnNext;
    ImageView imgCongratulation;
    String words = "", wordUse = "", wordENGMeaning = "", wordMarathiMeaning = "", flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_success);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // BackgroundSoundService.player.stop();
        // BackgroundSoundService.player.release();
        final MediaPlayer player = MediaPlayer.create(this, R.raw.correct_answer);
        player.setLooping(false); // Set looping
        player.setVolume(100, 100);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.stop();
                player.release();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 2000L);
       /* // Sound pool new instance
        SoundPool spool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
// Sound pool on load complete listener
        spool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                Log.i("OnLoadCompleteListener", "Sound " + sampleId + " loaded.");
                boolean loaded = true;
            }
        });
// Load the sample IDs
        int[] soundId = new int[3];
        soundId[0] = spool.load(this, R.raw.correct_answer, 1);
        */
        if (!UtilPref.getFromPrefs(this, "from", "true").equalsIgnoreCase("false")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ASR.getInstance(DialogSuccess.this).textToSpeech(DialogSuccess.this, "Congratulations... Superb");
                }
            }, 1000L);
        }
        if (bundle != null) {
            words = bundle.getString("Words");
            wordUse = bundle.getString("WordsUse");
            wordENGMeaning = bundle.getString("WordsEngMeaning");
            wordMarathiMeaning = bundle.getString("WordsMarathiMeaning");
            flag = bundle.getString("Flag");

        }
        setUI();
    }

    public void setUI() {
        txtWord = (TextView) findViewById(R.id.txt_word);
        txtWordEngMeaning = (TextView) findViewById(R.id.txt_word_english_meaning);
        txtWordMarathiMeaning = (TextView) findViewById(R.id.txt_word_marathi_meaning);
        txtUseLink = (TextView) findViewById(R.id.txt_word_use_link);
        txtWordUse = (TextView) findViewById(R.id.txt_word_use);
        txtTitle = (TextView) findViewById(R.id.title);
        title = (TextView) findViewById(R.id.txt_dailog_title);
        imgCongratulation = (ImageView) findViewById(R.id.image_dialog);
        txtUseLink.setText("Use of Word");
        txtUseLink.setPaintFlags(txtUseLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnNext = (Button) findViewById(R.id.btn_next_level);
        btnNext.setOnClickListener(this);
        txtUseLink.setOnClickListener(this);
        if (flag.trim().equalsIgnoreCase("true")) {
            txtWordUse.setVisibility(View.GONE);
            txtWord.setText(words);
            txtWordEngMeaning.setText(" - " + wordENGMeaning);
            txtWordMarathiMeaning.setText(" - " + wordMarathiMeaning);

        } else {
            txtTitle.setText(getString(R.string.txt_your_word));
            title.setText("");
            txtWordUse.setVisibility(View.GONE);
            imgCongratulation.setVisibility(View.GONE);
            txtWord.setText(words);
            txtWordEngMeaning.setText(" - " + wordENGMeaning);
            txtWordMarathiMeaning.setText(" - " + wordMarathiMeaning);


        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_level:
                Intent intent = new Intent();
                setResult(0);
                finish();
                break;
            case R.id.txt_word_use_link:
                txtWordUse.setVisibility(View.VISIBLE);
                txtWordUse.setText(" - " + wordUse);
                break;
        }
    }

}