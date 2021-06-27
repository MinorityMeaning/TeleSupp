package com.mardaunt.telesupp.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages_table")
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String phone;
    private String text;
    //@ColumnInfo(name = "nature")
    //private String mNature;
    //@ColumnInfo(name = "service")
    //private String mService;
    //@ColumnInfo(name = "date")
    //private String mDate;

    public Message(int id,
                   @NonNull String phone,
                   @NonNull String text
                   //@NonNull String nature,
                   //@NonNull String service,
                   //@NonNull String date
                    ) {
        this.id = id;
        this.phone = phone;
        this.text = text;
        //this.mNature = nature;
        //this.mService = service;
        //this.mDate = date;
    }

    public String getPhone(){return this.phone;}
    public String getText(){return this.text;}
    public int getId(){return this.id;}
    //public void setPhone(String phone){this.mPhone = phone;}
    //public void setText(String text){this.mText = text;}
    //public String getNature(){return this.mNature;}
    //public String getService(){return this.mService;}
    //public String getDate(){return this.mDate;}
}
