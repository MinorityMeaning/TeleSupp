package com.mardaunt.telesupp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mardaunt.telesupp.MainActivity;
import com.mardaunt.telesupp.MaskPhone;
import com.mardaunt.telesupp.R;
import com.mardaunt.telesupp.recyclerview.HelperAdapter;
import com.mardaunt.telesupp.recyclerview.MessageListAdapter;
import com.mardaunt.telesupp.room.MessageViewModel;

public class WhatsAppFragment extends Fragment {

    private final MainActivity mainActivity = (MainActivity) getContext();

    public static WhatsAppFragment newInstance() {
        return new WhatsAppFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_whatsapp, container, false);

        //Установим маску телефона.
        MaskPhone.setUpMaskRu(view.findViewById(R.id.edit_phone));
        ToggleButton toggle = view.findViewById(R.id.toggle_ru_mask);
        toggle.setChecked(true);

        //Настраиваем RecyclerView для сообщений
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final MessageListAdapter adapter = new MessageListAdapter(new MessageListAdapter.MessageDiff(), HelperAdapter.getHelperAdapter());
        recyclerView.setAdapter(adapter);
            //Настроим прокрутку до последнего сообщения.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
            //Получим ViewModel от ViewModelProvider
        MessageViewModel mMessageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
            //Добавим наблюдателя для LiveData
        mMessageViewModel.getAllMessages().observe(getViewLifecycleOwner(), messages -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(messages);
        });
            //Передадим mMessageViewModel для MainActivity
        HelperAdapter.getHelperAdapter().setMessageViewModel(mMessageViewModel);

        return view;
    }
}
