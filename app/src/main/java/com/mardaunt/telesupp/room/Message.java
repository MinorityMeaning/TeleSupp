package com.mardaunt.telesupp.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages_table")
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String phone;
    private String text;
    private String nature;
    //@ColumnInfo(name = "service")
    //private String mService;
    //@ColumnInfo(name = "date")
    //private String mDate;

    public Message(int id,
                   @NonNull String phone,
                   @NonNull String text,
                   @NonNull String nature
                   //@NonNull String service,
                   //@NonNull String date
                    ) {
        this.id = id;
        this.phone = phone;
        this.text = text;
        this.nature = nature;
        //this.mNature = nature;
        //this.mService = service;
        //this.mDate = date;
    }

    public int getId(){return this.id;}
    public String getPhone(){return this.phone;}
    public String getText(){return this.text;}
    public String getNature(){return this.nature;}
    //public String getService(){return this.mService;}
    //public String getDate(){return this.mDate;}
}
