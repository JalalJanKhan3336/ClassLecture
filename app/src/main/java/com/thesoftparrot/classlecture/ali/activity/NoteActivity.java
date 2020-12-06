package com.thesoftparrot.classlecture.ali.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.ali.adapter.NoteListAdapter;
import com.thesoftparrot.classlecture.ali.dialog.FriendListBottomSheetDialogFragment;
import com.thesoftparrot.classlecture.ali.notification.MyNotificationManager;
import com.thesoftparrot.classlecture.ali.room.dao.NoteDao;
import com.thesoftparrot.classlecture.ali.room.database.LocalStorage;
import com.thesoftparrot.classlecture.ali.room.entity.Note;
import com.thesoftparrot.classlecture.databinding.ActivityNoteBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        mBinding.showFriendsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendListBottomSheetDialogFragment dialog = new FriendListBottomSheetDialogFragment();
                dialog.show(getSupportFragmentManager(), dialog.getTag());
            }
        });

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

    // Will bind list to recyclerview
    private void bindList(List<Note> list) {
        NoteListAdapter adapter = new NoteListAdapter(this, list);
        LinearLayoutManager layout = new LinearLayoutManager(this);

        mBinding.recyclerView.setLayoutManager(layout);
        mBinding.recyclerView.setAdapter(adapter);
    }

}