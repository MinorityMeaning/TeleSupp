package com.mardaunt.telesupp.recyclerview;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.mardaunt.telesupp.R;
import com.mardaunt.telesupp.room.Message;
import com.mardaunt.telesupp.room.TimeStampConverter;

public class MessageListAdapter extends ListAdapter<Message, MessageViewHolder> {

    public MessageListAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback) {
        super(diffCallback);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getNature().equals("outgoing"))
            return R.layout.recyclerview_item_outgoing;
        else
            return R.layout.recyclerview_item_incoming;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MessageViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message current = getItem(position);

        holder.bind(current.getPhone(),
                    current.getText(),
                    TimeStampConverter.getTime(current.getDate())); // Бинтим телефон сообщение и время.
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

