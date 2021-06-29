package com.mardaunt.telesupp.room;

import android.util.Log;

import androidx.room.TypeConverter;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeStampConverter {
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    static DateFormat dfTime = new SimpleDateFormat("HH:mm", Locale.getDefault());


    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String getTime(Date date){
        return dfTime.format(date);
    }



}
