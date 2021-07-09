package com.mardaunt.telesupp.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.mardaunt.telesupp.R;
import com.mardaunt.telesupp.room.Message;
import com.mardaunt.telesupp.room.TimeStampConverter;

public class MessageListAdapter extends ListAdapter<Message, MessageViewHolder> {

    HelperAdapter helperAdapter;

    public MessageListAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback, HelperAdapter helperAdapter) {
        super(diffCallback);
        this.helperAdapter = helperAdapter;
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
                    TimeStampConverter.getTime(current.getDate())); // Бинтим телефон сообщение и время

            // Добавим все view CheckBox в list чтобы скрывать и показывать их по мере необходимости
        helperAdapter.addCheckBox(holder.getMessageCheckBox());

            // Установим слушатель короткого нажатия по сообщению.
        holder.getMessageBox().setOnClickListener(v -> {
            EditText editPhone = helperAdapter.getMainActivity()
                                    .findViewById(R.id.edit_phone);
            editPhone.setText(current.getPhone());
        });

            // Установим слушатель долгого нажатия по сообщению.
        holder.getMessageBox().setOnLongClickListener(v -> {
            helperAdapter.getMainActivity()
                         .findViewById(R.id.trash_button)
                         .setVisibility(View.VISIBLE);
            helperAdapter.getMainActivity()
                    .findViewById(R.id.close_button)
                    .setVisibility(View.VISIBLE);

            for (CheckBox checkBox: helperAdapter.getSetCheckBox())
                checkBox.setVisibility(View.VISIBLE);
            return false;
        });
            // Установим слушатели для чекбоксов, которые переключают переменную isSelected
        holder.getMessageCheckBox().setOnClickListener(v -> {
            current.isSelected = ((CheckBox) v).isChecked();

            helperAdapter.addCheckBox((CheckBox) v);
        });
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

