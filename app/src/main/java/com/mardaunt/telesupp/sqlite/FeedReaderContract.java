package com.mardaunt.telesupp.sqlite;

import android.provider.BaseColumns;

public class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract(){}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "messages";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_MESSAGE = "message";
        public static final String COLUMN_NAME_NATURE = "nature";  // incoming or outgoing
        public static final String COLUMN_NAME_SERVICE = "service";  // WhatsApp or Telegram
        public static final String COLUMN_NAME_DATE = "date";  // WhatsApp or Telegram
    }

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_PHONE + " TEXT," +
                    FeedEntry.COLUMN_NAME_MESSAGE + " TEXT," +
                    FeedEntry.COLUMN_NAME_NATURE + " TEXT," +
                    FeedEntry.COLUMN_NAME_SERVICE + " TEXT," +
                    FeedEntry.COLUMN_NAME_DATE + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
