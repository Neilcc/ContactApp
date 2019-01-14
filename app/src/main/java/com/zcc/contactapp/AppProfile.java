package com.zcc.contactapp;

import android.content.Context;

public abstract class AppProfile {
    public static final String AppName = "ContactApp";

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    static void setContext(Context context) {
        sContext = context.getApplicationContext();
    }

}