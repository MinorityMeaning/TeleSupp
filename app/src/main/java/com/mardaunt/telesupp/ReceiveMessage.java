package com.mardaunt.telesupp;

import android.annotation.SuppressLint;
import android.util.Log;

import com.mardaunt.telesupp.room.Message;
import com.mardaunt.telesupp.room.MessageViewModel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ReceiveMessage {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    String answer;
    JSONObject json;
    boolean looping;
    UserData userData;
    MessageViewModel mMessageViewModel;

    ReceiveMessage(MessageViewModel mMessageViewModel) {
        this.mMessageViewModel = mMessageViewModel;
        this.looping = true;
        this.userData = UserData.getUserData();
    }

    public void setStatusLooping(boolean status) {
        looping = status;
    }

    public void startSendRequests() {

        new Thread(() -> {
            while (looping) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getReceiveRequest(userData.getUserId());
            }
        }).start();
    }



    public void getReceiveRequest(String user) {

        Request request = new Request.Builder()
                .url("http://18.217.19.171:8080/tuk_tuk/" + user)
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assert response.body() != null;
                 answer = response.body().string();
                    try {
                        json = new JSONObject(answer);
                        if (json.length() != 1) {
                                // Room SQL
                            Message mes = new Message(0, json.getString("phone"),
                                                            json.getString("message"),
                                                    "incoming",
                                                    "WhatsApp",
                                                            new Date());
                            mMessageViewModel.insert(mes);
                        }
                    } catch (JSONException e) {
                        Log.d("ErrorGetReceiveRequest", e.getMessage());
                    }
            }
        });
    }
}
