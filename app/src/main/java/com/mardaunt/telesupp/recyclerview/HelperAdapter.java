package com.mardaunt.telesupp.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;

import com.mardaunt.telesupp.MainActivity;
import com.mardaunt.telesupp.R;
import com.mardaunt.telesupp.room.Message;
import com.mardaunt.telesupp.room.MessageViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HelperAdapter {

    private static HelperAdapter helperAdapter;
    private MessageViewModel mMessageViewModel;
    private MainActivity mainActivity;
    private final HashSet<CheckBox> listCheckBox = new HashSet<>();

    public void deleteSelected() {
        LiveData<List<Message>> temp = mMessageViewModel.getAllMessages();
        List<Message> allMessages = temp.getValue();

        if (allMessages != null)
        for(Message message: allMessages)
            if(message.isSelected)
                mMessageViewModel.delete(message);

        // Скроем иконку корзины и чекбоксы
        mainActivity.findViewById(R.id.trash_button).setVisibility(View.GONE);
        mainActivity.findViewById(R.id.close_button).setVisibility(View.GONE);
        for (CheckBox checkBox: listCheckBox)
            checkBox.setVisibility(View.GONE);
    }

    public void setMessageViewModel(MessageViewModel messageViewModel){
        mMessageViewModel = messageViewModel;
    }

    public MessageViewModel getMessageViewModel() { return mMessageViewModel; }

    public HashSet<CheckBox> getSetCheckBox() {return listCheckBox;}

    public void addCheckBox(CheckBox checkBox) {
        listCheckBox.add(checkBox);
    }

    public void removeCheckBox(CheckBox checkBox){
        listCheckBox.remove(checkBox);
    }

    public int sizeCheckBox() {return listCheckBox.size();}

    public static HelperAdapter getHelperAdapter(){
        if (helperAdapter == null) helperAdapter = new HelperAdapter();
        return helperAdapter;
    }

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() { return mainActivity; }
}