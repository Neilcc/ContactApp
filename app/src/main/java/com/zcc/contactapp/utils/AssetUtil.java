package com.zcc.contactapp.utils;

import android.content.res.AssetManager;

import com.zcc.contactapp.ContactApplication;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtil {

    public static InputStream loadAsset(String fileName) throws IOException {
        AssetManager assetManager = ContactApplication.sApplicationContext.getAssets();
        return assetManager.open(fileName);
    }
}
