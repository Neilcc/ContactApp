package com.zcc.contactapp.contactmodel.repository;

import com.google.gson.reflect.TypeToken;
import com.zcc.contactapp.utils.AssetUtil;
import com.zcc.contactapp.utils.GsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactDataRepository {
    private static final String CONTACT_FILE = "Contacts.json";

    private static final Type CONTACT_DATA_LIST_TYPE = new TypeToken<ArrayList<ContactDataBean>>() {
    }.getType();

    public List<ContactDataBean> getData() {
        String contactInfo = loadContactData(CONTACT_FILE);
        if (contactInfo.isEmpty()) {
            return new ArrayList<>();
        } else {
            return GsonUtil.getInstance().fromJson(contactInfo, CONTACT_DATA_LIST_TYPE);
        }
    }

    private String loadContactData(String assetFileName) {
        try {
            InputStreamReader inputStreamReader
                    = new InputStreamReader(AssetUtil.loadAsset(assetFileName));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder Result = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null)
                Result.append(line);
            return Result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
