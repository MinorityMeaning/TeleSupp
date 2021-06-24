package com.mardaunt.telesupp.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BaseActions {
    public BaseActions(SQLiteDatabase db) {
        this.db = db;
    }

    private SQLiteDatabase db;

    public void addMessage(String phone, String message, String nature, String service, String date){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE, phone);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MESSAGE, message);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NATURE, nature);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SERVICE, service);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, date);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
    }

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    String[] projection = {
            BaseColumns._ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_MESSAGE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_NATURE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_DATE
    };

    public Cursor getMessages() {
        // Filter results WHERE "nature" = 'incoming'
        //String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_NATURE + " = ?";
        //String[] selectionArgs = {"incoming"};

        // How you want the results sorted in the resulting Cursor
        //String sortOrder = FeedReaderContract.FeedEntry._ID + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,                             // The array of columns to return (pass null to get all)
                null, //selection,              // The columns for the WHERE clause
                null, //selectionArgs,       // The values for the WHERE clause
                null,                           // don't group the rows
                null,                            // don't filter by row groups
                null //sortOrder                // The sort order

        );
        return cursor;
    }

}
