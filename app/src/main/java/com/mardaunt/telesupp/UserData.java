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

    private static UserData userData;

    private final MainActivity mainActivity;
    private SharedPreferences sPref;
    private SharedPreferences.Editor editor;
    final private String USER_ID = "user_id";

    public static void create(MainActivity activity) {
        if (userData == null) userData = new UserData(activity);
    }

    public static UserData getUserData() {
        return userData;
    }

    void createUser() {
        sPref = mainActivity.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        editor = sPref.edit();
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

    public void addLastReceive(String phone, String message) {
        editor.putString("last_receive", phone + ": " + message);
        editor.apply();
    }

    public String getLastReceive() {return sPref.getString("last_receive", "none");}
}