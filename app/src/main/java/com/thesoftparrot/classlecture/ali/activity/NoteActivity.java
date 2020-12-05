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

        subscribeNotification();

        initRef();
        click();
    }

    private void subscribeNotification() {

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

                packNotificationInJsonForm(title, detail);
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

    private void packNotificationInJsonForm(String title, String detail){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String sendTo = "/topics/"+userId;

        // Pack into Json Object
        JSONObject dataJson = new JSONObject();
        JSONObject outerJson = new JSONObject();

        try {
            dataJson.put("title", title);
            dataJson.put("message", detail);

            outerJson.put("to", sendTo);
            outerJson.put("data", dataJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendNotificationDataToServer(outerJson);
    }

    // Shows notification
    private void sendNotificationDataToServer(JSONObject notification) {

        String contentType = "application/json";
        String url = "https://fcm.googleapis.com/fcm/send";
        String serverKey = "key="+getResources().getString(R.string.server_key);

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", serverKey);
        map.put("Content-Type", contentType);

        AndroidNetworking.initialize(this);
        AndroidNetworking
                .post(url)
                .setPriority(Priority.IMMEDIATE)
                .addHeaders(map)
                .addJSONObjectBody(notification)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", "_onResponse_Json: "+response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("TAG", "_onError_Failed: "+anError.getErrorBody());
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