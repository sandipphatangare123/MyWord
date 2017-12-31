package com.sandip.myword;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandip.adapter.RecyclerAdapterChapter;
import com.sandip.model.ModelChapter;
import com.sandip.sqliteDbHelper.BeanChapter;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.OnItemClickListenerChapter;
import com.sandip.util.UtilPref;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public class ActivityChapterScreen extends AppCompatActivity implements OnItemClickListenerChapter, View.OnClickListener {
    String TAG = "ActivityChapterScreen";
    private Toolbar toolbar;
    ArrayList<ModelChapter> listOfModelChapters = new ArrayList<>();
    RecyclerAdapterChapter adapter;
    private LinearLayoutManager mLayoutManager;
    RecyclerView recyclerView;
    public static final int RequestPermissionCode = 1;

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setUI();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission();
        } else {
            // Proceed as we need not get the permission
        }

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(ActivityChapterScreen.this, new String[]
                {
                      /*  CAMERA,
                        READ_CONTACTS,
                        READ_PHONE_STATE,
                        RECEIVE_SMS,
                        SEND_SMS,
                        PROCESS_OUTGOING_CALLS,
                        WRITE_EXTERNAL_STORAGE,*/
                        ACCESS_NETWORK_STATE
                }, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {
                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                   /* boolean ReadContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean reciveSMS = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean sendSMS = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean processoutgoing = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean writeextrenal = grantResults[6] == PackageManager.PERMISSION_GRANTED;
                    boolean access_network = grantResults[7] == PackageManager.PERMISSION_GRANTED;*/
                }

                break;
        }
    }

    public void setUI() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_home_screen);

        String s=UtilPref.getFromPrefs(this,"theme","day");
        if(s.equalsIgnoreCase("night")) {
            relativeLayout.setBackgroundResource(R.drawable.night_theme_solar);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.bg);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView woodTitle = (TextView) findViewById(R.id.txt_wood_title);
        woodTitle.setText(getString(R.string.title_activity_game_chapter));
        ImageView imageView = (ImageView) findViewById(R.id.img_back);
        imageView.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_level_view);
        listOfModelChapters = new ArrayList<>();
        BeanChapter beanGetChapter = new BeanChapter();
        listOfModelChapters = beanGetChapter.getChapterList(this);
       /* for(int i=0;i<listOfModelChapters.size();i++){
            Log.e(TAG,"listOfChapters    =>"+listOfModelChapters.get(i).getChapterName());
        }*/

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new RecyclerAdapterChapter(this, listOfModelChapters, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(ModelChapter item) {
        item.setClickable(true);
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(ActivityChapterScreen.this, ActivityLevelScreen.class);
        intent.putExtra("ChapterName", item.getChapterName() + "");
        UtilPref.saveToPrefs(this, "chapterName", item.getChapterName() + "");
        startActivity(intent);
    }

}