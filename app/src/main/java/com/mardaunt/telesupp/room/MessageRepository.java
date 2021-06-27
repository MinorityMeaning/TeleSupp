package com.mardaunt.telesupp.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class MessageRepository {

    private MessageDao mMessageDao;
    private LiveData<List<Message>> mAllMessages;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    MessageRepository(Application application) {
        MessageRoomDatabase db = MessageRoomDatabase.getDatabase(application);
        mMessageDao = db.messageDao();
        mAllMessages = mMessageDao.getMessages();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Message>> getAllMessages() {
        return mAllMessages;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Message message) {
        MessageRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMessageDao.insert(message);
        });
    }
}

