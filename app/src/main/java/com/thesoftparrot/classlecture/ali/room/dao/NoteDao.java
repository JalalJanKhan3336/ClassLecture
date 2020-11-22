package com.thesoftparrot.classlecture.ali.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.thesoftparrot.classlecture.ali.room.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void addNewNote(Note note);

    @Query("select * from notes")
    List<Note> getAllNotes();
}
