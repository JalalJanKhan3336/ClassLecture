package com.thesoftparrot.classlecture.ali.notification;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyMessagingService extends FirebaseMessagingService {
    public MyMessagingService() {}

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onNewToken(@NonNull String newToken) {
        super.onNewToken(newToken);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> data = remoteMessage.getData();

        String title = data.get("title");
        String message = data.get("message");

        // TODO: Pass title & message in Notification
        MyNotificationManager myNotificationManager = new MyNotificationManager(this);
        myNotificationManager.sendNotification(title, message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}