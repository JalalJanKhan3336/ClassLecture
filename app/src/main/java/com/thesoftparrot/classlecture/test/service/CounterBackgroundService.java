package com.thesoftparrot.classlecture.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.thesoftparrot.classlecture.test.viewmodel.CounterViewModel;

public class CounterBackgroundService extends Service {
    private static final String TAG = "CounterBackgroundServic";

    private static long counter = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate_Called: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy_Called: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand_Called: ");

        CounterViewModel counterViewModel = (CounterViewModel) intent.getSerializableExtra("counter_viewmodel");
        Log.d(TAG, "onStartCommand_ViewModel: "+counterViewModel);

        if(counterViewModel != null)
            counterViewModel.updateCounter(counter++);

        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind_Called: ");
        return null;
    }
}
