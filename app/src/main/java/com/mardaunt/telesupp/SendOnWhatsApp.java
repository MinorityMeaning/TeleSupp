package com.mardaunt.telesupp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

public class SendOnWhatsApp {
    SendOnWhatsApp(String phone, String message, Activity mainActivity) {
        this.phone = phone;
        this.message = message;
        this.mainActivity = mainActivity;
    }

    private String phone;
    private String message;
    private Activity mainActivity;

    //Отправка через клиентский WhatsApp

    @SuppressLint("QueryPermissionsNeeded")
    public void send(){
        //PackageManager packageManager = mainActivity.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        try {
            String url = "https://api.whatsapp.com/send?phone=" + phone + "&text=" + message;
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            //if (i.resolveActivity(packageManager) != null) {
                mainActivity.startActivity(i);
            //}
        } catch (Exception e) {
            Log.d("ErrorSendOnWhatsApp", e.getMessage());
        }
    }
}
