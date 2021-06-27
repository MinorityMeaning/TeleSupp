package com.mardaunt.telesupp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mardaunt.telesupp.MainActivity;
import com.mardaunt.telesupp.R;
import com.mardaunt.telesupp.recyclerview.MessageListAdapter;

public class WhatsAppFragment extends Fragment {

    public static WhatsAppFragment newInstance() {
        return new WhatsAppFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_whatsapp, container, false);
    }
}
