package com.mardaunt.telesupp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.mardaunt.telesupp.recyclerview.HelperAdapter;

public class GetMessageService extends Service {

    ReceiveMessage receiveMessage;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        receiveMessage = new ReceiveMessage(HelperAdapter.getHelperAdapter().getMessageViewModel());
        receiveMessage.setStatusLooping(true);

        receiveMessage.startSendRequests();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Incoming Not Updated", Toast.LENGTH_LONG).show();
        receiveMessage.setStatusLooping(false);
    }
}
