package com.zcc.contactapp.contactmodel.repository;

import android.os.AsyncTask;

import com.google.gson.reflect.TypeToken;
import com.zcc.contactapp.base.repository.ILoadDataListener;
import com.zcc.contactapp.utils.AssetUtil;
import com.zcc.contactapp.utils.GsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactDataRepository {

    public static final String CONTACT_FILE = "Contacts.json";
    public static final String IMAGE_FOLDER = "/images";

    private static final Type CONTACT_DATA_LIST_TYPE = new TypeToken<ArrayList<ContactDataBean>>() {
    }.getType();

    public static List<ContactDataBean> loadContactDataSync() {
        String contactInfo = loadStringFromAsset(CONTACT_FILE);
        if (contactInfo.isEmpty()) {
            return new ArrayList<>();
        } else {
            return GsonUtil.getInstance().fromJson(contactInfo, CONTACT_DATA_LIST_TYPE);
        }
    }

    private static String loadStringFromAsset(String assetFileName) {
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

    public void loadContactDataAsync(final ILoadDataListener<List<ContactDataBean>> listener) {
        AsyncTask<Void, Void, List<ContactDataBean>> loadDataTask = new LoadContactDataAsyncTask(listener);
        loadDataTask.execute();
    }

    private static class LoadContactDataAsyncTask extends AsyncTask<Void, Void, List<ContactDataBean>> {
        private ILoadDataListener<List<ContactDataBean>> listener;

        public LoadContactDataAsyncTask(ILoadDataListener<List<ContactDataBean>> listener) {
            this.listener = listener;
        }

        @Override
        protected List<ContactDataBean> doInBackground(Void... voids) {
            return loadContactDataSync();
        }

        @Override
        protected void onPostExecute(List<ContactDataBean> contactDataBeans) {
            listener.onSuccess(contactDataBeans);
        }
    }
}
