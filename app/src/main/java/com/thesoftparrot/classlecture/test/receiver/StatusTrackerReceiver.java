package com.thesoftparrot.classlecture.test.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

public class StatusTrackerReceiver extends BroadcastReceiver {
    private static final String TAG = "StatusTrackerReceiver";

    public static StatusTrackerCallback mStatusTrackerCallback;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "_onReceive_Class: "+context.getClass().getSimpleName());
        Log.d(TAG, "_onReceive_Intent: "+intent);

        if(intent != null) {
            Log.d(TAG, "_onReceive_Intent_Action: " + intent.getAction());
            mStatusTrackerCallback.onInternetStatusChanged(isConnectedToInternet(intent));
        }
    }

    private boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnectedOrConnecting());
        }

        return false;
    }

    private boolean isConnectedToInternet(Intent intent){
        if(intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            return ni != null && ni.getState() == NetworkInfo.State.CONNECTED;
        }

        return false;
    }

    public interface StatusTrackerCallback {
        void onInternetStatusChanged(boolean isConnected);
    }

}
