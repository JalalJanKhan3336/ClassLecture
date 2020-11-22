package com.thesoftparrot.classlecture.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.databinding.ActivityAuthBinding;
import com.thesoftparrot.classlecture.databinding.ActivityCounterBinding;
import com.thesoftparrot.classlecture.test.service.CounterBackgroundService;
import com.thesoftparrot.classlecture.test.viewmodel.CounterViewModel;
import com.thesoftparrot.classlecture.test.viewmodel.CounterViewModelFactory;

import java.io.Serializable;

public class CounterActivity extends AppCompatActivity {

    private ActivityCounterBinding mBinding;
    private CounterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCounterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        viewModel = new ViewModelProvider(CounterActivity.this, new CounterViewModelFactory()).get(CounterViewModel.class);
        viewModel.getCounterLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long counter) {
                mBinding.counterTv.setText(String.valueOf(counter));
            }
        });

        mBinding.startCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBackgroundService();
            }
        });

        mBinding.stopCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopBackgroundService();
            }
        });
    }

    private void startBackgroundService() {
        Intent intent = new Intent(CounterActivity.this, CounterBackgroundService.class);
        startService(intent);
    }

    private void stopBackgroundService() {
        Intent intent = new Intent(CounterActivity.this, CounterBackgroundService.class);
        stopService(intent);
    }
}