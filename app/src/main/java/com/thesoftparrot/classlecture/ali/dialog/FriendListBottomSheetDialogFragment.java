package com.thesoftparrot.classlecture.ali.dialog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.ali.activity.MessageActivity;
import com.thesoftparrot.classlecture.ali.adapter.FriendListAdapter;
import com.thesoftparrot.classlecture.ali.callback.ListItemClickListener;
import com.thesoftparrot.classlecture.ali.model.Friend;
import com.thesoftparrot.classlecture.databinding.FragmentFriendListBottomSheetDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class FriendListBottomSheetDialogFragment extends BottomSheetDialogFragment implements ListItemClickListener {

    private FragmentFriendListBottomSheetDialogBinding mBinding;

    public FriendListBottomSheetDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentFriendListBottomSheetDialogBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fetchFriendList();
    }

    private void fetchFriendList() {
        final List<Friend> list = new ArrayList<>();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef
                .child("Friends")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot ds : snapshot.getChildren()){
                                if(ds != null && ds.exists()){
                                    Friend friend = ds.getValue(Friend.class);

                                    if(friend != null){
                                        list.add(friend);
                                    }
                                }
                            }

                            bindListToRecyclerView(list);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void bindListToRecyclerView(List<Friend> list) {
        FriendListAdapter adapter = new FriendListAdapter(requireContext(), list, this);
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClicked(Friend friend, int position) {
        Intent intent = new Intent(requireContext(), MessageActivity.class);
        intent.putExtra("friend_key", friend);
        startActivity(intent);
    }
}