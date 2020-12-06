package com.thesoftparrot.classlecture.ali.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.snackbar.Snackbar;
import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.ali.model.Friend;
import com.thesoftparrot.classlecture.databinding.ActivityMessageBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MessageActivity extends AppCompatActivity {

    private ActivityMessageBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMessageBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        final Friend friend = (Friend) getIntent().getSerializableExtra("friend_key");

        mBinding.sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = mBinding.msgBoxEt.getText().toString().trim();
                if(TextUtils.isEmpty(msg)){
                    mBinding.msgBoxEt.setError("Type something...");
                }else {
                    sendTypedMessageToFriend("New Message", msg, friend.getUserId());
                }
            }
        });
    }

    private void sendTypedMessageToFriend(String title, String message, String friendId){

        String sendTo = "/topics/"+friendId;

        // Pack into Json Object
        JSONObject dataJson = new JSONObject();
        JSONObject outerJson = new JSONObject();

        try {
            dataJson.put("title", title);
            dataJson.put("message", message);

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
                        Snackbar.make(mBinding.getRoot(), "Message sent", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("TAG", "_onError_Failed: "+anError.getErrorBody());
                        Snackbar.make(mBinding.getRoot(), "Message Not Sent", Snackbar.LENGTH_LONG).show();
                    }
                });
    }
}