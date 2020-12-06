package com.thesoftparrot.classlecture.ali.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesoftparrot.classlecture.ali.callback.ListItemClickListener;
import com.thesoftparrot.classlecture.ali.model.Friend;
import com.thesoftparrot.classlecture.databinding.ItemFriendBinding;

import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.MyViewHolder> {

    private Context mContext;
    private List<Friend> mFriendList;
    private ListItemClickListener itemClickListener;

    private ItemFriendBinding mBinding;

    public FriendListAdapter(Context mContext, List<Friend> mFriendList, ListItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.mFriendList = mFriendList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ItemFriendBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new MyViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Friend friend = mFriendList.get(position);
        mBinding.nameTv.setText(friend.getEmail());
        mBinding.ageTv.setText(String.valueOf(friend.getAge()));

        // Click to open message room
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener != null)
                    itemClickListener.onItemClicked(friend, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
