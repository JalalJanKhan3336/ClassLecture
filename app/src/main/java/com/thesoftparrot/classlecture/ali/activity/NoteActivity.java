package com.thesoftparrot.classlecture.ali.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.ali.adapter.NoteListAdapter;
import com.thesoftparrot.classlecture.ali.notification.MyNotificationManager;
import com.thesoftparrot.classlecture.ali.room.dao.NoteDao;
import com.thesoftparrot.classlecture.ali.room.database.LocalStorage;
import com.thesoftparrot.classlecture.ali.room.entity.Note;
import com.thesoftparrot.classlecture.databinding.ActivityNoteBinding;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private ActivityNoteBinding mBinding;

    private NoteDao noteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initRef();
        click();
    }

    private void initRef() {
        noteDao = LocalStorage.getInstance().getNoteDao();
    }

    private void click() {
        mBinding.addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mBinding.titleEt.getText().toString().trim();
                String detail = mBinding.detailEt.getText().toString().trim();

                if(TextUtils.isEmpty(title)){
                    mBinding.titleEt.setError("Title is Required");
                    return;
                }

                if(TextUtils.isEmpty(detail)){
                    mBinding.detailEt.setError("Detail is Required");
                    return;
                }

                Note note = new Note(title, detail);
                noteDao.addNewNote(note);

                Snackbar.make(mBinding.getRoot(), title+" note added", Snackbar.LENGTH_LONG).show();

                sendNotificationDataToServer(title, detail);
            }
        });

        mBinding.getAllNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Note> list = noteDao.getAllNotes();

                if(list != null && list.size() > 0){
                    bindList(list);
                }else
                    Snackbar.make(mBinding.getRoot(), "No Notes found", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    // Shows notification
    private void sendNotificationDataToServer(String title, String detail) {
        MyNotificationManager myNotificationManager = new MyNotificationManager(this);
        myNotificationManager.sendNotification(title, detail);
    }

    // Will bind list to recyclerview
    private void bindList(List<Note> list) {
        NoteListAdapter adapter = new NoteListAdapter(this, list);
        LinearLayoutManager layout = new LinearLayoutManager(this);

        mBinding.recyclerView.setLayoutManager(layout);
        mBinding.recyclerView.setAdapter(adapter);
    }
}