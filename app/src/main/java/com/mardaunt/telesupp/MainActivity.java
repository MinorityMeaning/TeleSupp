package com.mardaunt.telesupp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;


import com.mardaunt.telesupp.fragments.WhatsAppFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public String postUrl= "http://94.228.114.114:8080/test";

    //public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Изначально подгружаем fragment_whatsapp.xml
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, WhatsAppFragment.newInstance());
        ft.commit();
        //Настраиваем слушатель для нижнего меню
        BottomNavigationView navigation = findViewById(R.id.navigation);
        MenuListener menuListener = new MenuListener(this);
        navigation.setOnNavigationItemSelectedListener(menuListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Установим маску телефона.
        MaskPhone.setUpMaskRu(findViewById(R.id.edit_phone));
        ToggleButton toggle = findViewById(R.id.toggle_ru_mask);
        toggle.setChecked(true);
    }

    void postRequest(String postUrl,String phone, String message) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        /*
        //RequestBody body = RequestBody.create(JSON, postBody);
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("message", message)
                .build();

        Request request = new Request.Builder()
                .url(postUrl)
                .method("POST", body)
                .build();
        //System.out.println(request);
*/
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone", phone);
            jsonObject.put("message", message);
            jsonObject.put("service", "WhatsApp");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonObject.toString());
        Request request = new Request.Builder()
                .url(postUrl)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG",response.body().string());
            }
        });
    }

    //ОнКлик кнопки "Отправить"
    public void sendMessage(View view){

        // Получаем текстовое поле в текущей Activity
        EditText editPhone = (EditText) findViewById(R.id.edit_phone);
        EditText editMessage = (EditText) findViewById(R.id.edit_message);

        String phone = editPhone.getText().toString();
        String message = editMessage.getText().toString();
        if (phone.equals("") || message.equals("")) return;
        // Проверяем отправку через клиентский WhatsApp
        CheckBox checkBox = findViewById(R.id.anonim);
        if(!checkBox.isChecked()) {(new SendOnWhatsApp(phone, message, this)).send(); return;}

        //Если checkBox отмечен то продолжим серверную отправку.
        new TimerButton( (Button) view, this).start();// Выключим кнопку на 8 секунд
        //Пробуем сделать запрос
        try {
            postRequest(postUrl, phone, message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        editPhone.setText("");
        editMessage.setText("");
        //TextView status = findViewById(R.id.home_text);
        Toast.makeText(this, getString(R.string.status_send), Toast.LENGTH_LONG).show();
        //status.setText("Удалось отправить!");

    }
    // ОнКлик включателя маски для РФ
    public void onToggleRuMask(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            MaskPhone.setUpMaskRu(findViewById(R.id.edit_phone));
            ToggleButton toggle = findViewById(R.id.toggle_all_mask);
            toggle.setChecked(false);
            EditText input = findViewById(R.id.edit_phone);
            input.setHint(R.string._7_xxx_xxx_xx_xx);
        }
    }

    // ОнКлик включателя маски для всех стран
    public void onToggleAllMask(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            MaskPhone.setUpMaskAll(findViewById(R.id.edit_phone));
            ToggleButton toggle = findViewById(R.id.toggle_ru_mask);
            toggle.setChecked(false);
            EditText input = findViewById(R.id.edit_phone);
            input.setHint(R.string._x_xxx_xxx_xx_xx);
        }
    }

}