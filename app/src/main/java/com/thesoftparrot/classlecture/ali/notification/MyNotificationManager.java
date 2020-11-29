package com.thesoftparrot.classlecture.ali.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.thesoftparrot.classlecture.R;

public class MyNotificationManager {

    private Context context;
    private static final String channelId = "channel_id_1";
    private static final String channelName = "channel_name";

    public MyNotificationManager(Context context) {
        this.context = context;
    }

    // sends Notification
    public void sendNotification(String title, String msg){

        // Creates Notification Channel for API Level 27 or above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);

        builder.setAutoCancel(false);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setSound(soundUri);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Send
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(3336, builder.build());
    }

}
