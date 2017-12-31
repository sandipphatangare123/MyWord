package com.sandip.myword;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sandip.customdialog.DialogFailed;
import com.sandip.customdialog.DialogSuccess;
import com.sandip.model.ModelWord;
import com.sandip.model.ModelWords;
import com.sandip.util.ASR;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.Util;
import com.sandip.util.UtilPref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ActivityPlayBoard extends AppCompatActivity implements View.OnClickListener {

    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;
    Button btnNext, btnRefresh, btnHint;
    LinearLayout linContainer;
    LinearLayout linChild;
    RelativeLayout rootLayout;

    ArrayList<String> arrayList = new ArrayList<String>();
    List<String> listOfWords = new ArrayList<String>();
    ArrayList<ModelWord> listOfParseWords = null;
    String jsonWordSting = null;
    ModelWord modelWord;
    int positionOfarrayelement = 0, currentPosition = 0;
    int clickPosition = 0;
    String textShuffleWord = "", textExactWord = "", textASRWord = "", textWordForTTS = "";
    TextView txtLevel, txtHint, txtTimer;
    boolean flag = false;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    int level = 0;
    String level1 = "";
    Handler handler;
    int Seconds = 0, Minutes = 0, MilliSeconds;
    ArrayList<ModelWords> listOfModelWordsLevel = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playboard);
        ASR.getInstance(this).textToSpeech(this, "Start game by clicking start button");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            level = bundle.getInt("Level");
            level1 = bundle.getString("Level1");
            if (level == 1) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel1");
            } else if (level == 2) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel2");
            } else if (level == 3) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel3");
            } else if (level == 4) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel4");
            } else if (level == 5) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel5");
            } else if (level == 6) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel6");
            } else if (level == 7) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel7");
            } else if (level == 8) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel8");
            } else if (level == 9) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel9");
            } else if (level == 10) {
                listOfModelWordsLevel = (ArrayList<ModelWords>) bundle.getSerializable("ModelWordsLevel10");
            }
        } else {
            Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
        }

        setUI();
        //  loadJSONFromAsset();
        Collections.shuffle(listOfModelWordsLevel);
        parseJson();
        handler = new Handler();
    }


    private void createView(String alphate) {
        Log.e("Play of alphate==->", alphate);
        Minutes = 0;
        MilliSeconds = 0;
        textExactWord = "";
        textShuffleWord = "";
        alphate = alphate.replace(" ", "");
        textExactWord = alphate.trim();
        txtHint.setVisibility(View.GONE);
        linContainer.setVisibility(View.VISIBLE);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        linContainer.startAnimation(hyperspaceJumpAnimation);
        if (!flag) {
            flag = true;
            btnHint.setVisibility(View.VISIBLE);
            btnRefresh.setVisibility(View.VISIBLE);

            Animation trLeft = AnimationUtils.loadAnimation(this, R.anim.tranlate_in);
            btnHint.startAnimation(trLeft);

            Animation trRight = AnimationUtils.loadAnimation(this, R.anim.tranlate_in_right);
            btnRefresh.startAnimation(trRight);
        }
        linContainer.removeAllViews();
        createInvisibleView();
        for (int i = 0; i < 10; i++) {

            String name = "img_" + (i + 1);
            int id = getResources().getIdentifier(name, "id", getPackageName());

            if (i < alphate.length()) {
                Log.e("Play of visible->", name);
                findViewById(id).setVisibility(View.VISIBLE);
                int resourceId = Util.getViewOfChar(alphate.charAt(i) + "", this);
                findViewById(id).setBackgroundResource(resourceId);
                ((TextView) findViewById(id)).setText(alphate.charAt(i) + "".toUpperCase());
                findViewById(id).setOnClickListener(this);
                findViewById(id).setClickable(true);
                //  findViewById(id).setTag(alphate.charAt(i) + "".toUpperCase());
                Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
                findViewById(id).startAnimation(bounce);
            } else {
                Log.e("Play of invisible->", name);
                findViewById(id).setTag("");
                findViewById(id).setClickable(false);

                findViewById(id).setVisibility(View.GONE);

            }
            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
        }

    }


    private void createInvisibleView() {
        for (int i = 0; i < 10; i++) {
            String name = "img_" + (i + 1);
            int id = getResources().getIdentifier(name, "id", getPackageName());
            findViewById(id).setVisibility(View.GONE);
            findViewById(id).setClickable(false);
            findViewById(id).clearAnimation();
        }
    }

    private void createViewHorizontal(String ch) {
        textASRWord = textExactWord;
        textShuffleWord = textShuffleWord + ch;
        //  String id=getImageResource((TextView) view);
        LinearLayout child_1 = (LinearLayout) getLayoutInflater().inflate(R.layout.imageview, null);
        TextView txt1 = (TextView) child_1.findViewById(R.id.text_child);
        //    txtWord.setText(txtWord.getText().toString()+""+((TextView)view).getText().toString());

        int resourceId = Util.getViewOfChar(ch + "", this);

        //  txt1.setBackgroundResource(R.drawable.word);

        txt1.setText(ch + "".toUpperCase());

         /* Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.movetop);
         child_1.startAnimation(hyperspaceJumpAnimation);*/
        linContainer.addView(child_1);

        if (textShuffleWord.trim().length() == textExactWord.trim().length()) {
            if (textShuffleWord.trim().equalsIgnoreCase(textExactWord.trim())) {
                // Toast.makeText(this, "Your word is mathced", Toast.LENGTH_LONG).show();
                handler.removeCallbacks(runnable);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UtilPref.saveToPrefs(ActivityPlayBoard.this, "from", "true");
                        ASR.getInstance(ActivityPlayBoard.this).textToSpeech(ActivityPlayBoard.this, textASRWord);
                        Intent intent = new Intent(ActivityPlayBoard.this, DialogSuccess.class);
                        intent.putExtra("Words", listOfParseWords.get(currentPosition).getWord());
                        intent.putExtra("WordsUse", listOfParseWords.get(currentPosition).getWordUse());
                        intent.putExtra("WordsEngMeaning", listOfParseWords.get(currentPosition).getWordMeaningEnglish());
                        intent.putExtra("WordsMarathiMeaning", listOfParseWords.get(currentPosition).getGetWordMeaningMarathi());
                        intent.putExtra("Flag", "true");
                        startActivityForResult(intent, 0);
                    }
                }, 500L);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.removeCallbacks(runnable);
                        ASR.getInstance(ActivityPlayBoard.this).textToSpeech(ActivityPlayBoard.this, "Sorry!! word does not match");
                    }
                }, 1000L);
                Intent intent = new Intent(ActivityPlayBoard.this, DialogFailed.class);
                intent.putExtra("Words", listOfParseWords.get(currentPosition).getWord());
                intent.putExtra("WordsUse", listOfParseWords.get(currentPosition).getWordUse());
                intent.putExtra("WordsEngMeaning", listOfParseWords.get(currentPosition).getWordMeaningEnglish());
                intent.putExtra("WordsMarathiMeaning", listOfParseWords.get(currentPosition).getGetWordMeaningMarathi());
                startActivityForResult(intent, 1);
                //Toast.makeText(this, "Your word is incoreected,please click on refresh", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getImageResource(TextView iv) {
        return (String) iv.getTag();
    }

    private void setUI() {
        LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.lin_bashboard);
        txtLevel = (TextView) findViewById(R.id.toolbar_title);

        String s = UtilPref.getFromPrefs(this, "theme", "day");
        if (s.equalsIgnoreCase("night")) {
            relativeLayout.setBackgroundResource(R.drawable.night_theme_solar);
            txtLevel.setTextColor(getResources().getColor(R.color.colorWhite));
        } else {
            relativeLayout.setBackgroundResource(R.drawable.bg);
            txtLevel.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }
        linContainer = (LinearLayout) findViewById(R.id.linear);
        linChild = (LinearLayout) findViewById(R.id.lin_childview);

        rootLayout = (RelativeLayout) findViewById(R.id.root_layout);

        btnNext = (Button) findViewById(R.id.btn_next_word);
        btnNext.setText(getString(R.string.btn_start));
        btnNext.setOnClickListener(this);

        btnHint = (Button) findViewById(R.id.btn_hint);
        btnHint.setOnClickListener(this);

        ImageView imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);


        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(this);

        txtHint = (TextView) findViewById(R.id.txt_hint);

        txtTimer = (TextView) findViewById(R.id.txt_timer);
        txtTimer.setVisibility(View.VISIBLE);

        txtLevel.setVisibility(View.VISIBLE);
        txtLevel.setText(UtilPref.getFromPrefs(this, "chapterName", ""));

    }

    @Override
    public void onClick(View view) {
        String ch = "";
        switch (view.getId()) {
            case R.id.btn_next_word:
                txtHint.setVisibility(View.GONE);
                btnNext.setText(getString(R.string.btn_next));
                if ((positionOfarrayelement) < listOfParseWords.size()) {
                    createInvisibleView();
                    currentPosition = positionOfarrayelement;
                    createView(listOfParseWords.get(positionOfarrayelement).getWord());
                    positionOfarrayelement++;
                } else {
                    // Toast.makeText(this, "Level completed babu please click on back", Toast.LENGTH_LONG).show();
                    positionOfarrayelement = 0;
                }
                break;
            case R.id.btn_refresh:
                createInvisibleView();
                createView(listOfParseWords.get(currentPosition).getWord());
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.img_1:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_2:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_3:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_4:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_5:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_6:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_7:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_8:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_9:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.img_10:
                ch = ((TextView) view).getText().toString();
                createAnimatedView(ch, view);
                break;
            case R.id.btn_hint:
                String firstLetter = "";
                String lastLetter = "";
                char[] myHint = new char[textExactWord.trim().length()];

                char[] hint = textExactWord.trim().toCharArray();
                for (int i = 0; i < hint.length; i++) {
                    if (i == 0) {
                        myHint[i] = hint[i];
                        firstLetter = String.valueOf(hint[i]);


                    } else if (i == hint.length - 1) {
                        myHint[i] = hint[i];
                        lastLetter = String.valueOf(hint[i]);
                    } else {
                        myHint[i] = '-';
                    }
                }
                String wordHints = Arrays.toString(myHint);
                Log.e("ActivityPlayBoard", "firstLetter====>>**" + firstLetter);
                Log.e("ActivityPlayBoard", "lastLetter====>>**" + lastLetter);
                Util.setToolTips(this, "Your word First character is " + firstLetter.toUpperCase() + "  and last character is  " + lastLetter.toUpperCase(), view);
                break;
        }
    }

    private void createAnimatedView(String ch, View view) {
        if (!ch.equalsIgnoreCase("")) {
            // txtWord.setText(ch);
            ASR.getInstance(this).textToSpeech(this, ch);
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide);
            view.startAnimation(hyperspaceJumpAnimation);
            createViewHorizontal(ch);
            ((TextView) view).setClickable(false);
        }
    }

    public Runnable runnable = new Runnable() {

        public void run() {
            if (Minutes == 1) {
                Intent intent = new Intent(ActivityPlayBoard.this, DialogFailed.class);
                intent.putExtra("Words", listOfParseWords.get(currentPosition).getWord());
                intent.putExtra("WordsUse", listOfParseWords.get(currentPosition).getWordUse());
                intent.putExtra("WordsEngMeaning", listOfParseWords.get(currentPosition).getWordMeaningEnglish());
                intent.putExtra("WordsMarathiMeaning", listOfParseWords.get(currentPosition).getGetWordMeaningMarathi());
                startActivityForResult(intent, 1);
                txtTimer.setText("" + Minutes + ":"
                        + String.format("%02d", Seconds));
                handler.removeCallbacks(this);

                // Toast.makeText(ActivityPlayBoard.this, "Your time up", Toast.LENGTH_LONG).show();
            } else {
                MillisecondTime = SystemClock.uptimeMillis() - StartTime;

                UpdateTime = TimeBuff + MillisecondTime;

                Seconds = (int) (UpdateTime / 1000);

                Minutes = Seconds / 60;

                Seconds = Seconds % 60;

                txtTimer.setText("" + Minutes + ":"
                        + String.format("%02d", Seconds));
                if (Seconds == 10) {

                } else {
                    //  Toast.makeText(ActivityPlayBoard.this, "Please take hint for reference", Toast.LENGTH_SHORT).cancel();

                }

                handler.postDelayed(this, 0);
            }
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            createInvisibleView();
            createView(listOfParseWords.get(currentPosition).getWord());
        } else if (requestCode == 0) {
            txtHint.setVisibility(View.VISIBLE);
        }

    }

    public StringBuilder shuffle(String input) {
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        System.out.println(output.toString());
        return output;
    }

   /* public String loadJSONFromAsset() {
        try {

            InputStream is = getAssets().open("word.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            jsonWordSting = new String(buffer, "UTF-8");
            Log.d("Tag>>>>>>>>>>>>.", jsonWordSting);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonWordSting;


    }*/

    public void parseJson() {

        listOfParseWords = new ArrayList<>();
       /* if (jsonWordSting != null) {
            JSONObject jsonObject = null;
            try {
                JSONArray jsonArray = new JSONArray(jsonWordSting);
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        modelWord = new ModelWord();


                        jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.length() != 0) {

                            if (!jsonObject.isNull("word")) {
                                modelWord.setWord(jsonObject.getString("word"));
                            }
                            if (!jsonObject.isNull("wordMeaningEnglish"))
                                modelWord.setWordMeaningEnglish(jsonObject.getString("wordMeaningEnglish"));

                            if (!jsonObject.isNull("wordMeaningMarathi"))
                                modelWord.setGetWordMeaningMarathi(jsonObject.getString("wordMeaningMarathi"));
                        }
                        Log.d("Tag>>>>>>", listOfParseWords + "");
                        listOfParseWords.add(modelWord);

                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }*/
        if (listOfModelWordsLevel.size() > 0) {
            for (int i = 0; i < listOfModelWordsLevel.size(); i++) {
                modelWord = new ModelWord();
                modelWord.setWord(listOfModelWordsLevel.get(i).getWord());
                modelWord.setWordMeaningEnglish(listOfModelWordsLevel.get(i).getWordsEngMeaning());
                modelWord.setGetWordMeaningMarathi(listOfModelWordsLevel.get(i).getWordsMarathiMeaning());
                modelWord.setWordUse(listOfModelWordsLevel.get(i).getWordUse());
                listOfParseWords.add(modelWord);
            }
        }


    }


}
