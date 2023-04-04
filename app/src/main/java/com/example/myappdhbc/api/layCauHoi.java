package com.example.myappdhbc.api;

import android.os.AsyncTask;

import com.example.myappdhbc.DATA;
import com.example.myappdhbc.object.Cauhoi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class layCauHoi extends AsyncTask<Void, Void, Void> {
    String data;

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.1.222/duoihinhbatchu/layCauHoi.php")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string();
        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data != null) {
            // xử lý dữ liệu ở đây
            try {
                DATA.getData().arrCauHoi.clear();
                JSONArray array = new JSONArray(data);
                for (int i=0;i<array.length();i++){
                    JSONObject o = array.getJSONObject(i);
                    Cauhoi c= new Cauhoi();
                    c.anh =o.getString("anh");
                    c.ten =o.getString("ten");
                    c.dapAn =o.getString("dapan");
                    DATA.getData().arrCauHoi.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            // thông báo lỗi
        }
    }
}