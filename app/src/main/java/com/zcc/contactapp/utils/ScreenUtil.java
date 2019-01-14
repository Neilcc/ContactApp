package com.zcc.contactapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.zcc.contactapp.AppProfile;

import java.lang.reflect.Field;

public class ScreenUtil {
    private static final String TAG = AppProfile.AppName + ".ScreenUtil";
    /**
     * 屏幕宽度
     */
    private static int screenWidth;
    /**
     * 屏幕高度
     */
    private static int screenHeight;
    /**
     * 宽高中，较小的值
     */
    private static int screenMin;
    /**
     * 宽高中，较大的值
     */
    private static int screenMax;
    /**
     * 屏幕密度
     */
    private static float density;
    /**
     * 屏幕分辨率
     */
    private static float scaleDensity;
    /**
     * 横向dpi
     */
    private static float xdpi;
    /**
     * 竖向dpi
     */
    private static float ydpi;
    /**
     *
     */
    private static int densityDpi;
    /**
     * 状态栏高度
     */
    private static int statusBarHeight;
    /**
     * 底部导航高度
     */
    private static int navBarHeight;
    private static int sBarHeight = 0;
    private static double RATIO = 0.85;

    /**
     * 获取屏幕信息
     *
     * @param context 上下文
     */
    private static void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        statusBarHeight = getStatusBarHeight(context);
        navBarHeight = getNavBarHeight(context);
        screenHeight -= statusBarHeight;
        Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        return getStatusBarHeight(AppProfile.getContext());
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
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

    /**
     * 获取导航栏高度
     *
     * @param context 上下文
     * @return 底部导航栏高度
     */
    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * dp值转化为像素值
     *
     * @param dipValue dp值
     * @return 像素值
     */
    public static int dip2px(float dipValue) {
        final float scale = ScreenUtil.getDisplayDensity();
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 像素转dp
     *
     * @param pxValue 像素值
     * @return dp值
     */
    public static int px2dip(float pxValue) {
        final float scale = ScreenUtil.getDisplayDensity();
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDisplayDensity() {
        if (density == 0) {
            GetInfo(AppProfile.getContext());
        }
        return density;
    }

    /**
     * 获取屏幕的宽度
     *
     * @return 屏幕宽度
     */
    public static int getDisplayWidth() {
        if (screenWidth == 0) {
            GetInfo(AppProfile.getContext());
        }
        return screenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getDisplayHeight() {
        if (screenHeight == 0) {
            GetInfo(AppProfile.getContext());
        }
        return screenHeight;
    }

    /**
     * 获取宽高中，较小的值
     *
     * @return 宽高中，较小的值
     */
    public static int getScreenMin() {
        if (screenMin == 0) {
            GetInfo(AppProfile.getContext());
        }
        return screenMin;
    }

    /**
     * 获取宽高中，较大的值
     *
     * @return 宽高中，较大的值
     */
    public static int getScreenMax() {
        if (screenMin == 0) {
            GetInfo(AppProfile.getContext());
        }
        return screenMax;
    }

}
