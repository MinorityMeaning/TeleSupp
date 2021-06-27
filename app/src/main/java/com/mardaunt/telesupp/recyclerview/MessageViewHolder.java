package com.mardaunt.telesupp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mardaunt.telesupp.R;

class MessageViewHolder extends RecyclerView.ViewHolder {
    private final TextView messageItemView;

    private MessageViewHolder(View itemView) {
        super(itemView);
        messageItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        messageItemView.setText(text);
    }

    static MessageViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new MessageViewHolder(view);
    }
}

