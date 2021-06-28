package com.mardaunt.telesupp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mardaunt.telesupp.R;

class MessageViewHolder extends RecyclerView.ViewHolder {
    private final TextView phoneItemView;
    private final TextView messageItemView;

    private MessageViewHolder(View itemView) {
        super(itemView);
        messageItemView = itemView.findViewById(R.id.text_view_message);
        phoneItemView = itemView.findViewById(R.id.text_view_phone);
    }

    public void bind(String phone, String message) {
        phoneItemView.setText(phone);
        messageItemView.setText(message);
    }

        //viewType содержит id для нужного layout.
    static MessageViewHolder create(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
        return new MessageViewHolder(view);
    }
}

