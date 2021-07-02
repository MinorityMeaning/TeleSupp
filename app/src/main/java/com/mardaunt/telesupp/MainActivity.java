package com.mardaunt.telesupp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.mardaunt.telesupp.fragments.WhatsAppFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mardaunt.telesupp.recyclerview.MessageListAdapter;
import com.mardaunt.telesupp.room.Message;
import com.mardaunt.telesupp.room.MessageViewModel;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public String postUrl= "http://18.217.19.171:8080/add_message";
    private UserData userData;
    private ReceiveMessage receiveMessage;
    MessageViewModel mMessageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //Изначально подгружаем fragment_whatsapp.xml
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, WhatsAppFragment.newInstance());
        ft.commit();
            //Объект данных пользователя
        userData = new UserData(this);
        userData.createUser();
            //Настраиваем слушатель для нижнего меню. Передадим объекту меню userData,
           // чтобы работать с данными пользователя внутри других фрагментов.
        BottomNavigationView navigation = findViewById(R.id.navigation);
        MenuListener menuListener = new MenuListener(this, userData);
        navigation.setOnNavigationItemSelectedListener(menuListener);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        //Объект для запросов входящих сообщений
        receiveMessage = new ReceiveMessage(mMessageViewModel);
        receiveMessage.getReceiveRequest(userData.getUserId(), userData);

    }

    public void setMessageViewModel(MessageViewModel mVm) {
        mMessageViewModel = mVm;
    }
    public MessageViewModel getMessageViewModel(){
        return mMessageViewModel;
    }

    private void postRequest(String postUrl,String phone, String message) {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone", phone);
            jsonObject.put("message", message);
            jsonObject.put("service", "WhatsApp");
            jsonObject.put("user", userData.getUserId());
            jsonObject.put("status", "ok"); // Багаж для исполнителя automagic
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
                assert response.body() != null;
                Log.d("TAG",response.body().string());
            }
        });
    }

        //ОнКлик кнопки "Отправить"
    public void sendMessage(View view){

            // Получаем текстовое поле в текущей Activity
        EditText editPhone = findViewById(R.id.edit_phone);
        EditText editMessage = findViewById(R.id.edit_message);

        String phone = editPhone.getText().toString().replaceAll("[()\\s|-]+","");
        String message = editMessage.getText().toString();
        if (phone.equals("") || message.equals("")) return;

            // Проверяем отправку через клиентский WhatsApp
        CheckBox checkBox = findViewById(R.id.anonim);
        if(!checkBox.isChecked()) {(new SendOnWhatsApp(phone, message, this)).send(); return;}
            //Room SQL
        Message mes = new Message(0,phone, message, "outgoing", "WhatsApp", new Date());
        mMessageViewModel.insert(mes);

        //Если checkBox отмечен то продолжим серверную отправку.
        new TimerButton( (Button) view, this).start();// Выключим кнопку на 8 секунд
            //Пробуем сделать запрос
        postRequest(postUrl, phone, message);

        editMessage.setText("");
        Toast.makeText(this, getString(R.string.status_send), Toast.LENGTH_LONG).show();

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