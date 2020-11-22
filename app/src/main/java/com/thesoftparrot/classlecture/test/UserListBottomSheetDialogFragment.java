package com.thesoftparrot.classlecture.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.thesoftparrot.classlecture.databinding.FragmentUserListBottomSheetDialogBinding;

public class UserListBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private FragmentUserListBottomSheetDialogBinding mBinding;

    private UsernameListener mUsernameListener;

    public void setUsernameListener(UsernameListener mUsernameListener) {
        this.mUsernameListener = mUsernameListener;
    }

    public UserListBottomSheetDialogFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentUserListBottomSheetDialogBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        click();
    }

    private void click() {
        mBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mBinding.nameEt.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    mBinding.nameEt.setError("Name is required!");
                    return;
                }

                dismiss();
                mUsernameListener.onUsernameEntered(name);
            }
        });
    }

}