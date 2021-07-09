package com.mardaunt.telesupp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mardaunt.telesupp.R;

class MessageViewHolder extends RecyclerView.ViewHolder {
    private final TextView phoneItemView;
    private final TextView messageItemView;
    private final TextView timeItemView;
    private final CheckBox messageCheckBox;
    private final LinearLayout messageBox;

    private MessageViewHolder(View itemView) {
        super(itemView);
        messageItemView = itemView.findViewById(R.id.text_view_message);
        phoneItemView = itemView.findViewById(R.id.text_view_phone);
        timeItemView = itemView.findViewById(R.id.text_view_time);
        messageCheckBox = itemView.findViewById(R.id.message_check_box);
        messageBox = itemView.findViewById(R.id.linear_layout_message_box);
    }

    public void bind(String phone, String message, String time) {
        phoneItemView.setText(phone);
        messageItemView.setText(message);
        timeItemView.setText(time);
    }

    public LinearLayout getMessageBox() {return messageBox;}
    public CheckBox getMessageCheckBox() {return messageCheckBox;}

        //viewType содержит id для нужного layout сообщения.
    static MessageViewHolder create(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
        return new MessageViewHolder(view);
    }
}