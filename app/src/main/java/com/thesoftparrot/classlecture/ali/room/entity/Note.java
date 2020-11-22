package com.thesoftparrot.classlecture.ali.room.entity;

import android.annotation.SuppressLint;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    private long id;

    @ColumnInfo(name = "note_title")
    private String title;

    @ColumnInfo(name = "note_detail")
    private String detail;

    @ColumnInfo(name = "created_on_date")
    private String date;

    @ColumnInfo(name = "created_on_time")
    private String time;

    @Ignore
    public Note() {}

    public Note(String title, String detail) {
        this.title = title;
        this.detail = detail;
        this.date = getSystemDate();
        this.time = getSystemTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @SuppressLint("SimpleDateFormat")
    @Ignore
    private String getSystemDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    @Ignore
    private String getSystemTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

}
