package com.mardaunt.telesupp.recyclerview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.mardaunt.telesupp.room.Message;

public class MessageListAdapter extends ListAdapter<Message, MessageViewHolder> {

    public MessageListAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback) {
        super(diffCallback);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MessageViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message current = getItem(position);
        System.out.println(current.getPhone() + " " + current.getText());
        holder.bind(current.getText()); // Бинтим только тело сообщения! Закладка! Реализовать!
    }

    public static class MessageDiff extends DiffUtil.ItemCallback<Message> {

        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.getText().equals(newItem.getText());
        }
    }
}

