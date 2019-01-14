package com.zcc.contactapp.utils;

import android.util.Log;

import com.zcc.contactapp.BuildConfig;

public class DebugLog {
    private static boolean sEnabled = BuildConfig.DEBUG;

    public static void d(String tag, String desc) {
        if (sEnabled) {
            Log.d(tag, desc);
        }
    }

    public static void e(String tag, String desc) {
        if (sEnabled) {
            Log.e(tag, desc);
        }
    }
}
