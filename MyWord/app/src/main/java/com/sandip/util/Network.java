package com.sandip.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Ideologi on 5/7/16.
 */
public class Network {
    private static final String TAG="Network";
    public static boolean isConnectingToInternet(Context appContext) {
        // Method to check internet connection
        ConnectivityManager connectivity = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        try {

        } catch (Exception e) {
        }

        return false;
    }

    public static String getIMEI(Context context) {
        String identifier = null;
        TelephonyManager tm;
        try {
            tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null){
                identifier = tm.getDeviceId();
            }
            if (identifier == null || identifier.length() == 0){
                identifier = Secure.getString(context.getContentResolver(),Secure.ANDROID_ID);
            }
        } catch (Exception e) {
            return "Not Available";
        }
        return identifier;
    }

    public static String getLocalIpAddress() {
        // AndroidManifest.xml permissions
        // <uses-permission android:name="android.permission.INTERNET" />
        // <uses-permission
        // android:name="android.permission.ACCESS_NETWORK_STATE" />
        // TODO get IP Address
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress
                                .hashCode());
                        // Log.i(TAG, "***** IP="+ ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return "NA";
    }


    public void get(Context context) {
//        TelephonyManager tm = (TelephonyManager)getSystemService(context.TELEPHONY_SERVICE);
//        String countryCode = tm.getNetworkCountryIso();

    }

}
