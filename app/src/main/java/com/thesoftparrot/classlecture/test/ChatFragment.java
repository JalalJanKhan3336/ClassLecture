package com.thesoftparrot.classlecture.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thesoftparrot.classlecture.databinding.FragmentChatBinding;

public class ChatFragment extends Fragment {

    private FragmentChatBinding mBinding;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentChatBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        click();
    }

    // Will handle all clicks
    private void click() {
        mBinding.showBottomSheetDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserListBottomSheetDialogFragment bottomSheetDialogFragment = new UserListBottomSheetDialogFragment();
                bottomSheetDialogFragment.setCancelable(false);
                bottomSheetDialogFragment.show(getChildFragmentManager(),bottomSheetDialogFragment.getTag());

                bottomSheetDialogFragment.setUsernameListener(new UsernameListener() {
                    @Override
                    public void onUsernameEntered(String username) {
                        Toast.makeText(requireContext(), "Welcome "+username, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}