package com.thesoftparrot.classlecture.shaharyar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thesoftparrot.classlecture.databinding.ItemImageBinding;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.MyViewHolder> {

    private ItemImageBinding mItemBinding;

    @NonNull
    private Context mContext;
    private List<String> mImagesList;

    public ImageListAdapter(@NonNull Context mContext, List<String> mImagesList) {
        this.mContext = mContext;
        this.mImagesList = mImagesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemBinding = ItemImageBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new MyViewHolder(mItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String image = mImagesList.get(position);

        Glide
                .with(mContext)
                .load(image)
                .into(mItemBinding.imageIv);

    }

    @Override
    public int getItemCount() {
        return mImagesList.size();
    }

    // inner class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
