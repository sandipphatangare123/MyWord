package com.sandip.myword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import com.sandip.adapter.RecyclerAdapterSetting;
import com.sandip.model.ModelSetting;
import com.sandip.util.OnItemClickListenerSetting;
import com.sandip.util.Util;
import com.sandip.util.UtilPref;

import java.util.ArrayList;

public class ActivityChangeLanguage extends AppCompatActivity implements OnItemClickListenerSetting {
String TAG="ActivitySettingScreen";

    ArrayList<ModelSetting> listOfModelSetting=new ArrayList<>();
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

    public void setUI()
    {

        TextView woodTitle = (TextView)findViewById(R.id.txt_wood_title);
        woodTitle.setText(getString(R.string.txt_language));
        recyclerView = (RecyclerView)findViewById(R.id.recycler_level_view);
        listOfModelSetting=new ArrayList<>();

        listSettingsName=new ArrayList<>();
        listSettingsName.add("English("+getResources().getString(R.string.txt_english)+")");
        listSettingsName.add("Hindi("+getResources().getString(R.string.txt_hindi)+")");
        listSettingsName.add("Marathi("+getResources().getString(R.string.txt_marathi)+")");
        ArrayList<String> listSettingsTitle=new ArrayList<>();
        listSettingsTitle.add("English");
        listSettingsTitle.add("Hindi");
        listSettingsTitle.add("Marathi");


        for (int i = 0; i < listSettingsName.size(); i++) {
            ModelSetting modelLevel = new ModelSetting();
            modelLevel.setGameSettingTitle(listSettingsName.get(i));
            modelLevel.setSettingName(listSettingsTitle.get(i));
            listOfModelSetting.add(modelLevel);
        }

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new RecyclerAdapterSetting(this,listOfModelSetting,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(ModelSetting item) {
        if(item.getSettingName().trim().equalsIgnoreCase("English")){
            Util.setLocale("en",this);
            UtilPref.saveToPrefs(this,"langauge","en");
        } if(item.getSettingName().trim().equalsIgnoreCase("Hindi")){
            UtilPref.saveToPrefs(this,"langauge","hi");
            Util.setLocale("hi",this);
        } if(item.getSettingName().trim().equalsIgnoreCase("Marathi")){
            UtilPref.saveToPrefs(this,"langauge","mr");
            Util.setLocale("mr",this);
        }
    }

}