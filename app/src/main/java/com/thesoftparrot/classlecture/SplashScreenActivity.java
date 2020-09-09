package com.thesoftparrot.classlecture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thesoftparrot.classlecture.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";
    private ActivitySplashScreenBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SplashScreenActivity.this, "App Started", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Log.d(TAG, "onCreate_Called: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart_Called: ");
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy_Called: ");
    }

}