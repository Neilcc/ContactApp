package com.zcc.contactapp.utils;

import android.content.res.AssetManager;

import com.zcc.contactapp.AppProfile;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtil {

    public static InputStream loadAsset(String fileName) throws IOException {
        AssetManager assetManager = AppProfile.getContext().getAssets();
        return assetManager.open(fileName);
    }
}
