package com.sandip.myword;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sandip.adapter.RecyclerAdapterSetting;
import com.sandip.model.ModelSetting;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.OnItemClickListenerSetting;
import com.sandip.util.Util;
import com.sandip.util.UtilPref;

import java.util.ArrayList;

public class ActivityChangeTheme extends AppCompatActivity implements OnItemClickListenerSetting {
    String TAG = "ActivityChangeTheme";

    ArrayList<ModelSetting> listOfModelSetting = new ArrayList<>();
    ArrayList<String> listSettingsName;
    RecyclerAdapterSetting adapter;
    private LinearLayoutManager mLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        setUI();
    }

    public void setUI() {
        TextView woodTitle = (TextView) findViewById(R.id.txt_wood_title);
        woodTitle.setText(getString(R.string.txt_theme));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_level_view);
        listOfModelSetting = new ArrayList<>();

        listSettingsName = new ArrayList<>();
        listSettingsName.add("Day("+getResources().getString(R.string.txt_day)+")");
        listSettingsName.add("Night("+getResources().getString(R.string.txt_night)+")");

        ArrayList<String> listSettingsTitle=new ArrayList<>();
        listSettingsTitle.add("Day");
        listSettingsTitle.add("Night");
        for (int i = 0; i < listSettingsName.size(); i++) {
            ModelSetting modelLevel = new ModelSetting();
            modelLevel.setGameSettingTitle(listSettingsName.get(i));
            modelLevel.setSettingName(listSettingsTitle.get(i));
            listOfModelSetting.add(modelLevel);
        }

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new RecyclerAdapterSetting(this, listOfModelSetting, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(ModelSetting item) {
        if (item.getSettingName().trim().equalsIgnoreCase("Day")) {
            UtilPref.saveToPrefs(this, "theme", "day");

        }
        if (item.getSettingName().trim().equalsIgnoreCase("Night")) {
            UtilPref.saveToPrefs(this, "theme", "night");

        }
        Intent refresh = new Intent(this, ActivityWelcomeScreen.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(refresh);
        finish();
    }

}
