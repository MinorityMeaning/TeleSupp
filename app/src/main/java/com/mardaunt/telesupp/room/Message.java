package com.mardaunt.telesupp.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "messages_table")
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String phone;
    private String text;
    private String nature;
    private String service;
    private Date date;

    @Ignore
    public boolean isSelected;

    public Message(int id,
                   @NonNull String phone,
                   @NonNull String text,
                   @NonNull String nature,
                   @NonNull String service,
                   @NonNull Date date
                    ) {
        this.id = id;
        this.phone = phone;
        this.text = text;
        this.nature = nature;
        this.service = service;
        this.date = date;
    }

    public int getId(){return this.id;}
    public String getPhone(){return this.phone;}
    public String getText(){return this.text;}
    public String getNature(){return this.nature;}
    public String getService(){return this.service;}
    public Date getDate(){return this.date;}
}
