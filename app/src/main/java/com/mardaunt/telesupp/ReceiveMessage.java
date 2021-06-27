package com.mardaunt.telesupp;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.mardaunt.telesupp.room.Message;
import com.mardaunt.telesupp.room.MessageViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    MessageViewModel mMessageViewModel;

    ReceiveMessage(MessageViewModel mMessageViewModel) {
        this.mMessageViewModel = mMessageViewModel;
    }


    public void getReceiveRequest(String user, UserData userData) {
        //JSONObject receive;
        Request request = new Request.Builder()
                .url("http://192.168.0.10:8080/tuk_tuk/" + user)
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
                 System.out.println(answer);
                    try {
                        json = new JSONObject(answer);
                        if (json.length() != 1) {
                            userData.addLastReceive(json.getString("phone"), json.getString("message"));
                                // Room SQL
                            Message mes = new Message(0, json.getString("phone"), json.getString("message"));
                            mMessageViewModel.insert(mes);
                        }
                        System.out.println("Длина: " + json.length());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("Do not created json");
                    }
                //Log.d("TAG",response.body().string());
            }
        });
    }
}
