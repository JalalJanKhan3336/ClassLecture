package com.thesoftparrot.classlecture;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.thesoftparrot.classlecture.databinding.FragmentUserListBottomSheetDialogBinding;

public class UserListBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private FragmentUserListBottomSheetDialogBinding mBinding;

    private UsernameListener mUsernameListener;

    public void setUsernameListener(UsernameListener mUsernameListener) {
        this.mUsernameListener = mUsernameListener;
    }

    public UserListBottomSheetDialogFragment() {
        // Required empty public constructor
    }

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
                String value = mBinding.nameEt.getText().toString().trim();

                if(TextUtils.isEmpty(value)){
                    mBinding.nameEt.setError("Name is required!");
                    return;
                }

                dismiss();
                mUsernameListener.onUsernameEntered(value);

            }
        });
    }

}