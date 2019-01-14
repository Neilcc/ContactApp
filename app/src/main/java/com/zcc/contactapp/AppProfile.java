package com.zcc.contactapp;

import android.content.Context;

public abstract class AppProfile {
    public static final String AppName = "ContactApp";

    /* package */ static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context) {
        sContext = context;
    }

    public static final String getPackageName() {
        return sContext.getPackageName();
    }
}