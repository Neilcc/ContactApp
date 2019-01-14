package com.zcc.contactapp.utils;

import java.io.File;

public class UrlUtil {

    private static final String PICASSO_PATH_PREFIX = "file:///android_asset";

    public static String generateAssetUrl(String fileName, String folderPath) {
        return PICASSO_PATH_PREFIX + folderPath + File.separator + fileName;
    }
}
