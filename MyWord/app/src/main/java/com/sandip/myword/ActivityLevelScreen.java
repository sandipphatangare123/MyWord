package com.sandip.myword;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sandip.adapter.RecyclerAdapterLevel;
import com.sandip.model.ModelLevel;
import com.sandip.model.ModelWords;
import com.sandip.sqliteDbHelper.BeanWords;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.OnItemClickListenerLevel;
import com.sandip.util.UtilPref;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.PROCESS_OUTGOING_CALLS;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ActivityLevelScreen extends AppCompatActivity implements OnItemClickListenerLevel, View.OnClickListener {

    private Toolbar toolbar;
    ArrayList<ModelLevel> listOfLevel = new ArrayList<>();
    ArrayList<String> listNoOfLevel = new ArrayList<>();

    RecyclerAdapterLevel adapter;
    private LinearLayoutManager mLayoutManager;
    RecyclerView recyclerView;
    public static final int RequestPermissionCode = 1;
    String strChapterName = "";
    String TAG = "ActivityLevelScreen";
    ArrayList<ModelWords> listOfModelWords = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel1 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel2 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel3 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel4 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel5 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel6 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel7 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel8 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel9 = new ArrayList<>();
    ArrayList<ModelWords> listOfModelWordsLevel10 = new ArrayList<>();


    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            strChapterName = bundle.getString("ChapterName");
            Log.e("ActivityLevelScreen", "Name ==:" + strChapterName);
        }
        Log.e("ActivityLevelScreen", "Language@@ ==:" + Locale.getDefault());
        strChapterName = strChapterName.replaceAll("\\s+", "");
        BeanWords beanGetWords = new BeanWords();
        listOfModelWords = beanGetWords.getWords(this, strChapterName);
        for (int i = 0; i < listOfModelWords.size(); i++) {
          int size=listOfModelWords.get(i).getWord().trim().length();
            if (!listNoOfLevel.contains(size+"")) {
                listNoOfLevel.add(size+"");
                ModelLevel modelLevel = new ModelLevel();
                modelLevel.setGameLevel(listNoOfLevel.size()+"");
                modelLevel.setLetterSize(size+"");
                listOfLevel.add(modelLevel);
            }
            if (listOfModelWords.get(i).getWord().trim().length() == 3) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel1.add(modelRating);

            } else if (listOfModelWords.get(i).getWord().trim().length() == 4) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel2.add(modelRating);

            } else if (listOfModelWords.get(i).getWord().trim().length() == 5) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel3.add(modelRating);
            } else if (listOfModelWords.get(i).getWord().trim().length() == 6) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel4.add(modelRating);
            } else if (listOfModelWords.get(i).getWord().trim().length() == 7) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel5.add(modelRating);
            } else if (listOfModelWords.get(i).getWord().trim().length() == 8) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel6.add(modelRating);
            } else if (listOfModelWords.get(i).getWord().trim().length() == 9) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel7.add(modelRating);
            } else if (listOfModelWords.get(i).getWord().trim().length() == 10) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel8.add(modelRating);
            } else if (listOfModelWords.get(i).getWord().trim().length() == 11) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel9.add(modelRating);
            } else if (listOfModelWords.get(i).getWord().trim().length()== 12) {
                ModelWords modelRating = new ModelWords();
                modelRating.setWordsNo(listOfModelWords.get(i).getWordsNo());
                modelRating.setWord(listOfModelWords.get(i).getWord());
                modelRating.setWordsType(listOfModelWords.get(i).getWordsType());
                modelRating.setWordsEngMeaning(listOfModelWords.get(i).getWordsEngMeaning());
                modelRating.setWordsMarathiMeaning(listOfModelWords.get(i).getWordsMarathiMeaning());
                modelRating.setWordUse(listOfModelWords.get(i).getWordUse());
                listOfModelWordsLevel10.add(modelRating);
            }
        }
        setUI();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission();
        } else {
            // Proceed as we need not get the permission
        }

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(ActivityLevelScreen.this, new String[]
                {
                        CAMERA,
                        READ_CONTACTS,
                        READ_PHONE_STATE,
                        RECEIVE_SMS,
                        SEND_SMS,
                        PROCESS_OUTGOING_CALLS,
                        WRITE_EXTERNAL_STORAGE,
                        ACCESS_NETWORK_STATE
                }, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {
                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean reciveSMS = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean sendSMS = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean processoutgoing = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean writeextrenal = grantResults[6] == PackageManager.PERMISSION_GRANTED;
                    boolean access_network = grantResults[7] == PackageManager.PERMISSION_GRANTED;
                }

                break;
        }
    }

    public void setUI() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_home_screen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        String s=UtilPref.getFromPrefs(this,"theme","day");
        if(s.equalsIgnoreCase("night")) {
            relativeLayout.setBackgroundResource(R.drawable.night_theme_solar);
            mTitle.setTextColor(getResources().getColor(R.color.colorWhite));
        } else {
            relativeLayout.setBackgroundResource(R.drawable.bg);
            mTitle.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        mTitle.setText(UtilPref.getFromPrefs(this,"chapterName","").toUpperCase());

        ImageView imageView = (ImageView) findViewById(R.id.img_back);
        imageView.setOnClickListener(this);
        mTitle.setVisibility(View.VISIBLE);
        TextView woodTitle = (TextView) findViewById(R.id.txt_wood_title);
        woodTitle.setText(getString(R.string.title_activity_game_level_one));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_level_view);



        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new RecyclerAdapterLevel(this, listOfLevel, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(ModelLevel item, String pos, String Level) {
        item.setClickable(true);
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(ActivityLevelScreen.this, ActivityPlayBoard.class);
        Bundle bundle = new Bundle();
        bundle.putString("Level1", Level);
        if (pos.trim().equalsIgnoreCase("3")) {
            if (listOfModelWordsLevel1.size() > 0) {
                bundle.putSerializable("ModelWordsLevel1", (Serializable) listOfModelWordsLevel1);
                bundle.putInt("Level", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }


        } else if (pos.trim().equalsIgnoreCase("4")) {
            if (listOfModelWordsLevel2.size() > 0) {
                bundle.putSerializable("ModelWordsLevel2", (Serializable) listOfModelWordsLevel2);
                bundle.putInt("Level", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found", Toast.LENGTH_LONG).show();
            }
        } else if (pos.trim().equalsIgnoreCase("5")) {
            if (listOfModelWordsLevel3.size() > 0) {
                bundle.putSerializable("ModelWordsLevel3", (Serializable) listOfModelWordsLevel3);
                bundle.putInt("Level", 3);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }

        } else if (pos.trim().equalsIgnoreCase("6")) {
            if (listOfModelWordsLevel4.size() > 0) {
                bundle.putSerializable("ModelWordsLevel4", (Serializable) listOfModelWordsLevel4);
                bundle.putInt("Level", 4);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }
        } else if (pos.trim().equalsIgnoreCase("7")) {
            if (listOfModelWordsLevel5.size() > 0) {
                bundle.putSerializable("ModelWordsLevel5", (Serializable) listOfModelWordsLevel5);
                bundle.putInt("Level", 5);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }
        } else if (pos.trim().equalsIgnoreCase("8")) {
            if (listOfModelWordsLevel6.size() > 0) {
                bundle.putSerializable("ModelWordsLevel6", (Serializable) listOfModelWordsLevel6);
                bundle.putInt("Level", 6);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }
        } else if (pos.trim().equalsIgnoreCase("9")) {
            if (listOfModelWordsLevel7.size() > 0) {
                bundle.putSerializable("ModelWordsLevel7", (Serializable) listOfModelWordsLevel7);
                bundle.putInt("Level", 7);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }
        } else if (pos.trim().equalsIgnoreCase("10")) {
            if (listOfModelWordsLevel8.size() > 8) {
                bundle.putSerializable("ModelWordsLevel8", (Serializable) listOfModelWordsLevel8);
                bundle.putInt("Level", 8);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }
        } else if (pos.trim().equalsIgnoreCase("11")) {
            if (listOfModelWordsLevel9.size() > 0) {
                bundle.putSerializable("ModelWordsLevel9", (Serializable) listOfModelWordsLevel9);
                bundle.putInt("Level", 9);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }
        } else if (pos.trim().equalsIgnoreCase("12")) {
            if (listOfModelWordsLevel10.size() > 0) {
                bundle.putSerializable("ModelWordsLevel10", (Serializable) listOfModelWordsLevel10);
                bundle.putInt("Level", 10);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Word not found ", Toast.LENGTH_LONG).show();
            }
        }

    }

}