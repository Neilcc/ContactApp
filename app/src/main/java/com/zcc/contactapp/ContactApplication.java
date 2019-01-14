package com.zcc.contactapp;

import android.app.Application;
import android.content.Context;

public class ContactApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppProfile.setContext(this);
    }
}
