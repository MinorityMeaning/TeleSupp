package com.mardaunt.telesupp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserData {

    UserData(MainActivity mainActivity){this.mainActivity = mainActivity;}

    private final MainActivity mainActivity;
    private SharedPreferences sPref;
    final private String USER_ID = "user_id";

    void createUser() {
        sPref = mainActivity.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
            // Если user id уже есть, выходим из метода
        if(sPref.contains(USER_ID)) return;
        editor.putString(USER_ID, createUserId());
        editor.apply();
        Toast.makeText(mainActivity,"UserId created", Toast.LENGTH_SHORT).show();
    }
        // id формируется на основе текущей даты и времени
    private String createUserId() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    public String getUserId() {
        return sPref.getString(USER_ID, "");
    }
}