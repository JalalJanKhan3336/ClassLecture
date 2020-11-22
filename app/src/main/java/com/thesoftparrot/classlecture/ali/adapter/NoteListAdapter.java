package com.thesoftparrot.classlecture.ali.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesoftparrot.classlecture.ali.room.entity.Note;
import com.thesoftparrot.classlecture.databinding.ItemNoteBinding;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    private Context mContext;
    private List<Note> mNoteList;
    private ItemNoteBinding mBinding;

    public NoteListAdapter(Context mContext, List<Note> mNoteList) {
        this.mContext = mContext;
        this.mNoteList = mNoteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ItemNoteBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new MyViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = mNoteList.get(position);

        mBinding.noteIdTv.setText(String.valueOf(note.getId()));
        mBinding.titleTv.setText(note.getTitle());
        mBinding.detailTv.setText(note.getDetail());
        mBinding.timeTv.setText(note.getTime());
        mBinding.dateTv.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
