package com.zcc.contactapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.zcc.contactapp.AppProfile;

import java.lang.reflect.Field;

public class ScreenUtil {

    private static final String TAG = AppProfile.AppName + ".ScreenUtil";
    private static int sScreenWidth;
    private static int sScreenHeight;
    private static float sDensity;
    private static int statusBarHeight;
    private static int sBarHeight = 0;

    private static void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        sScreenWidth = dm.widthPixels;
        sScreenHeight = dm.heightPixels;
        sDensity = dm.density;
        statusBarHeight = getStatusBarHeight(context);
        sScreenHeight -= statusBarHeight;
        DebugLog.d(TAG, "sScreenWidth=" + sScreenWidth + " sScreenHeight=" + sScreenHeight + " sDensity=" + sDensity);
    }

    public static int getStatusBarHeight(Context context) {
        if (sBarHeight > 0) return sBarHeight;
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            sBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return sBarHeight;
    }

    public static int getDisplayWidth() {
        if (sScreenWidth == 0) {
            GetInfo(AppProfile.getContext());
        }
        return sScreenWidth;
    }

    public static int getDisplayHeight() {
        if (sScreenHeight == 0) {
            GetInfo(AppProfile.getContext());
        }
        return sScreenHeight;
    }
}
