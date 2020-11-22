package com.thesoftparrot.classlecture.ali.room.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thesoftparrot.classlecture.MyApp;
import com.thesoftparrot.classlecture.ali.room.dao.NoteDao;
import com.thesoftparrot.classlecture.ali.room.entity.Note;

@Database(entities = {Note.class}, version = 2)
public abstract class LocalStorage extends RoomDatabase {

    private static LocalStorage instance;

    public static LocalStorage getInstance() {
        if(instance == null)
            instance = initDatabaseRef();
        return instance;
    }

    private static LocalStorage initDatabaseRef() {
        return Room
                .databaseBuilder(
                        MyApp.getInstance().getApplicationContext(),
                        LocalStorage.class,
                        "notedb.db"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigrationFrom(1)
                .fallbackToDestructiveMigration()
                .build();

    }

    public abstract NoteDao getNoteDao();
}
