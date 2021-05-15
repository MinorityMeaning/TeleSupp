package com.mardaunt.telesupp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;


import com.mardaunt.telesupp.fragments.WhatsAppFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public String postUrl= "http://94.228.117.187:8083/send_app";

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

    void postRequest(String postUrl,String phone, String message) throws IOException {

        OkHttpClient client = new OkHttpClient();

        //RequestBody body = RequestBody.create(JSON, postBody);
        RequestBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("message", message)
                .build();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        //System.out.println(request);

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
        // Создаем объект Intent для вызова новой Activity
        //Intent intent = new Intent(this, MessageActivity.class);
        // Получаем текстовое поле в текущей Activity
        EditText editPhone = (EditText) findViewById(R.id.edit_phone);
        EditText editMessage = (EditText) findViewById(R.id.edit_message);

        String phone = editPhone.getText().toString();
        String message = editMessage.getText().toString();
        if (phone.equals("") || message.equals("")) return;
        //Подготовим содержимое запроса
        //postBody="{\n" +
        //        "    \"phone\": \"" + phone + "\",\n" +
        //        "    \"message\": \"" + message + "\"\n" +
        //        "}";
        //Пробуем сделать запрос
        new TimerButton(findViewById(R.id.button)).start();// Выключим кнопку на 15 секунд

        try {
            postRequest(postUrl, phone, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - значение этого объекта
        //intent.putExtra("message", message + phone);
        //startActivity(intent);
        //Очищаем поля после отправки.
        editPhone.setText("");
        editMessage.setText("");
        TextView status = findViewById(R.id.home_text);
        status.setText("Вроде удалось отправить!");

    }

}