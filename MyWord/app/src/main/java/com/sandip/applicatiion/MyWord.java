package com.sandip.applicatiion;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;

import com.sandip.util.ASR;
import com.sandip.util.BackgroundSoundService;
import com.sandip.util.LocaleManager;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MyWord extends Application {
    public static final String TAG = MyWord.class
            .getSimpleName();


    private static MyWord mInstance;
    private SharedPreferences _preferences;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        Log.e(TAG, "attachBaseContext");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e(TAG,"Applcation terminated");
       /* ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        Iterator<ActivityManager.RunningAppProcessInfo> iter = runningAppProcesses.iterator();

        while(iter.hasNext()){
            ActivityManager.RunningAppProcessInfo next = iter.next();

            String pricessName = getPackageName() + ":service";

            if(next.processName.equals(pricessName)){
                Process.killProcess(next.pid);
                break;
            }
        }*/
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"Applcation creaeted");
        mInstance = this;

    }

    public static synchronized MyWord getInstance() {
        return mInstance;
    }


    public void setLatoRegular(TextView textView) {
        AssetManager am = getApplicationContext().getAssets();
        Typeface typeFaceRegular = Typeface.createFromAsset(am, "fonts/Lato-Regular.ttf");
        textView.setTypeface(typeFaceRegular);
    }

    public void setMinionProSemibold(TextView textView) {
        AssetManager am = getApplicationContext().getAssets();
        Typeface typeFaceRegular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "MinionPro-Semibold.otf"));
        textView.setTypeface(typeFaceRegular);
    }

    public void setLatoBold(TextView textView) {
        AssetManager am = getApplicationContext().getAssets();
        Typeface typeFaceRegular = Typeface.createFromAsset(getAssets(), "fonts/lato_bold.ttf");
        textView.setTypeface(typeFaceRegular);
    }

    public void setLatoBoldItalic(TextView textView) {
        AssetManager am = getApplicationContext().getAssets();
        Typeface typeFaceRegular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Lato-BoldItalic.ttf"));
        textView.setTypeface(typeFaceRegular);
    }

    public void setLatoSemiBoldItalic(TextView textView) {
        AssetManager am = getApplicationContext().getAssets();
        Typeface typeFaceRegular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Lato-SemiboldItalic.ttf"));
        textView.setTypeface(typeFaceRegular);
    }

    public void setLatoItalic(TextView textView) {
        AssetManager am = getApplicationContext().getAssets();
        Typeface typeFaceRegular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Lato-Italic.ttf"));
        textView.setTypeface(typeFaceRegular);
    }


}
