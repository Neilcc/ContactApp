package com.zcc.contactapp.utils;

import com.google.gson.Gson;

public class GsonUtil {
    private static Gson instance;

    public static Gson getInstance() {
        if (instance == null) {
            synchronized (GsonUtil.class) {
                if (instance == null) {
                    instance = new Gson();
                }
            }
        }
        return instance;
    }

}
