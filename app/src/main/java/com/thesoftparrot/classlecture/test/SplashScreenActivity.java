package com.thesoftparrot.classlecture.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.thesoftparrot.classlecture.databinding.ActivitySplashScreenBinding;
import com.thesoftparrot.classlecture.test.receiver.StatusTrackerReceiver;

public class SplashScreenActivity extends AppCompatActivity implements StatusTrackerReceiver.StatusTrackerCallback {

    private static final String TAG = "SplashScreenActivity";
    private ActivitySplashScreenBinding mBinding;

    private StatusTrackerReceiver mStatusTrackerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mStatusTrackerReceiver = new StatusTrackerReceiver();
        StatusTrackerReceiver.mStatusTrackerCallback = this;

        mBinding.getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(SplashScreenActivity.this, "App Started", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();*/
                sendReceiver();
            }
        });

        Log.d(TAG, "onCreate_Called: ");
    }

    private void sendReceiver(){
        Intent intent = new Intent();
        intent.setAction("android.net.conn.CONNECTIVITY_CHANGE");
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        LocalBroadcastManager
                .getInstance(this)
                .sendBroadcast(intent);
    }

    private void startTrackingStatusReceiver() {
        /*IntentFilter filter = new IntentFilter("com.thesoftparrot.classlecture.ACTION_INTERNET");
        filter.addCategory(Intent.CATEGORY_DEFAULT);*/

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //registerReceiver(mStatusTrackerReceiver, filter);

        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(mStatusTrackerReceiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart_Called: ");
        startTrackingStatusReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume_Called: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause_Called: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart_Called: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop_Called: ");
        stopTrackingStatusReceiver();
    }

    private void stopTrackingStatusReceiver() {
        //unregisterReceiver(mStatusTrackerReceiver);
        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(mStatusTrackerReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy_Called: ");
    }

    @Override
    public void onInternetStatusChanged(boolean isConnected) {
        String msg = isConnected?"Connected" : "No Internet";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}