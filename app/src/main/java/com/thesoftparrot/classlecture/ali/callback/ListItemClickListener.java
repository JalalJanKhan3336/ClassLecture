package com.thesoftparrot.classlecture.ali.callback;

import com.thesoftparrot.classlecture.ali.model.Friend;

public interface ListItemClickListener {
    void onItemClicked(Friend friend, int position);
}
