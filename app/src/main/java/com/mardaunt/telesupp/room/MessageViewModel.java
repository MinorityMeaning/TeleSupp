package com.mardaunt.telesupp.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {

    private MessageRepository mRepository;

    private final LiveData<List<Message>> mAllMessages;

    public MessageViewModel (Application application) {
        super(application);
        mRepository = new MessageRepository(application);
        mAllMessages = mRepository.getAllMessages();
    }

    public LiveData<List<Message>> getAllMessages() { return mAllMessages; }

    public void insert(Message message) { mRepository.insert(message); }
}

