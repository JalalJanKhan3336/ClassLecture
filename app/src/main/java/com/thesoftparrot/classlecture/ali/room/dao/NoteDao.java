package com.thesoftparrot.classlecture.ali.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.thesoftparrot.classlecture.ali.room.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void addNewNote(Note note);

    @Insert
    void addNoteList(List<Note> noteList);

    @Update
    void updateExistingNote(Note note);

    @Update
    void updateExistingNoteList(List<Note> noteList);

    @Delete
    void deleteNote(Note note);

    @Delete
    void deleteNoteList(List<Note> noteList);

    @Query("delete from notes")
    void clearTable();

    @Query("select * from notes where noteId = :myId LIMIT 1")
    Note getSingleNote(long myId);

    @Query("select * from notes where note_title = :title AND note_detail = :detail LIMIT 1")
    Note getSingleNote(String title, String detail);

    @Query("select * from notes")
    List<Note> getAllNotes();

}
