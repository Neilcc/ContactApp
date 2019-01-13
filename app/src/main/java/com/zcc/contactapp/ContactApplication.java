package com.zcc.contactapp;

import android.app.Application;
import android.content.Context;

public class ContactApplication extends Application {

    public static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = this;
    }
}
