package com.thesoftparrot.classlecture.ali.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "persons")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;

}
