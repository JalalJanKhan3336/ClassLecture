package com.thesoftparrot.classlecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private Context mContext;
    private List<Contact> mContactList;

    public ContactListAdapter(Context mContext, List<Contact> mContactList) {
        this.mContext = mContext;
        this.mContactList = mContactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        ContactViewHolder holder = new ContactViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = mContactList.get(position);

        holder.imageView.setImageResource(contact.getImage());
        holder.nameTV.setText(contact.getName());
        holder.phoneTV.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    // inner Class
    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTV, phoneTV;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_iv);
            nameTV = itemView.findViewById(R.id.name_tv);
            phoneTV = itemView.findViewById(R.id.phone_tv);
        }
    }
}
