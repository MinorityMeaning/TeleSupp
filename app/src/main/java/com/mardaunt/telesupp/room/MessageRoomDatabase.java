package com.mardaunt.telesupp.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Message.class}, version = 9, exportSchema = false)
public abstract class MessageRoomDatabase extends RoomDatabase {

    public abstract MessageDao messageDao();

    private static volatile MessageRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MessageRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MessageRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MessageRoomDatabase.class, "messages_database6")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                MessageDao dao = INSTANCE.messageDao();
                dao.deleteAll();

                Message message = new Message(0,"+794434523456", "Hello, this incoming message!", "incoming");
                dao.insert(message);
            });

        }
    };

}
