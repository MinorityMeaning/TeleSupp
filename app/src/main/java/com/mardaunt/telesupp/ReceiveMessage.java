package com.mardaunt.telesupp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ReceiveMessage {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();

    private JSONObject getReceiveRequest(String url, String user) {
        JSONObject receive;
        Request request = new Request.Builder()
                .url(url + "/tuk_tuk/" + user)
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            receive = new JSONObject(String.valueOf(response));
        } catch (IOException | JSONException e) {
            Log.d("error_responce", e.getMessage());
            receive = new JSONObject();
        }
        return receive;
    }
}
