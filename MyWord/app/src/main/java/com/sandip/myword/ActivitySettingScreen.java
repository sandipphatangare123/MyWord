package com.sandip.myword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import com.sandip.adapter.RecyclerAdapterSetting;
import com.sandip.model.ModelSetting;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.OnItemClickListenerSetting;
import com.sandip.util.UtilPref;

import java.util.ArrayList;

public class ActivitySettingScreen extends AppCompatActivity implements OnItemClickListenerSetting {
    String TAG = "ActivitySettingScreen";

    ArrayList<ModelSetting> listOfModelSetting = new ArrayList<>();
    ArrayList<String> listSettingsName;
    RecyclerAdapterSetting adapter;
    private LinearLayoutManager mLayoutManager;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting_screen);
        setUI();


    }

    public void setUI() {

        TextView woodTitle = (TextView) findViewById(R.id.txt_wood_title);
        woodTitle.setText(getString(R.string.btn_settings));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_level_view);
        listOfModelSetting = new ArrayList<>();

        listSettingsName = new ArrayList<>();
        listSettingsName.add("Chapter (" + getResources().getString(R.string.title_activity_game_chapter) + ")");
        listSettingsName.add("Theme(" + getResources().getString(R.string.txt_theme) + ")");
        listSettingsName.add("Language(" + getResources().getString(R.string.txt_language) + ")");
        listSettingsName.add("Help(" + getResources().getString(R.string.btn_help) + ")");
        listSettingsName.add("Rate Us(" + getResources().getString(R.string.txt_rate_us) + ")");
        listSettingsName.add("Contact Us(" + getResources().getString(R.string.txt_contact_us) + ")");

        ArrayList<String> listSettingsTitle = new ArrayList<>();
        listSettingsTitle.add("Chapter");
        listSettingsTitle.add("Theme");
        listSettingsTitle.add("Language");
        listSettingsTitle.add("Help");
        listSettingsTitle.add("Rate Us");
        listSettingsTitle.add("Contact Us");

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
        UtilPref.saveToPrefs(this, "fromSettings", "true");
        if (item.getSettingName().trim().equalsIgnoreCase("Chapter")) {
            Intent i2 = new Intent(ActivitySettingScreen.this, ActivityChapterScreen.class);
            startActivity(i2);
            finish();
        }
        if (item.getSettingName().trim().equalsIgnoreCase("Theme")) {
            Intent i2 = new Intent(ActivitySettingScreen.this, ActivityChangeTheme.class);
            startActivity(i2);
            finish();
        }
        if (item.getSettingName().trim().equalsIgnoreCase("Language")) {
            Intent i2 = new Intent(ActivitySettingScreen.this, ActivityChangeLanguage.class);
            startActivity(i2);
            finish();
        }
        if (item.getSettingName().trim().equalsIgnoreCase("Help")) {
            Intent i2 = new Intent(ActivitySettingScreen.this, ActivityHelp.class);
            startActivity(i2);
            finish();
        }
        if (item.getSettingName().trim().equalsIgnoreCase("Rate Us")) {
            Intent i2 = new Intent(ActivitySettingScreen.this, ActivityContactUs.class);
            startActivity(i2);
            finish();
        }
        if (item.getSettingName().trim().equalsIgnoreCase("Contact Us")) {
            Intent i2 = new Intent(ActivitySettingScreen.this, ActivityContactUs.class);
            startActivity(i2);
            finish();
        }

    }

}