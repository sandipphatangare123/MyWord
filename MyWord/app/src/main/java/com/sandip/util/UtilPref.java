package com.sandip.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ishwar on 14/1/17.
 */

public class UtilPref {

     public static String TAG="WordGenrator";
    public static void saveToPrefs(Context context, String key, String value) {
        SharedPreferences prefs  = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getFromPrefs(Context context, String key, String defaultValue) {
        SharedPreferences sharedPrefs  = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        try {
            return sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static void deletePrefs(Context context, String key) {
        SharedPreferences prefs  = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        //editor.apply();
        editor.commit();
    }
}


