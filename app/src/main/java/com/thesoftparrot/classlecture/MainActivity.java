package com.thesoftparrot.classlecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.thesoftparrot.classlecture.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mBinding;

    private ContactListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        generateList();

        Log.d(TAG, "onCreate_Called: ");
    }

    private void generateList() {
        List<Contact> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            int image = R.drawable.ic_launcher_background;
            String name = "Name No. "+i;
            String phone = "Phone No. "+i;

            Contact contact = new Contact(image, name, phone);
            list.add(contact);
        }

        setupRecyclerView(list);
    }

    private void setupRecyclerView(List<Contact> list) {

        if(mAdapter == null)
            mAdapter = new ContactListAdapter(this,list);

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        layout.setSmoothScrollbarEnabled(true);
        mBinding.contactRecyclerView.setLayoutManager(layout);
        mBinding.contactRecyclerView.setHasFixedSize(false);
        mBinding.contactRecyclerView.setAdapter(mAdapter);
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