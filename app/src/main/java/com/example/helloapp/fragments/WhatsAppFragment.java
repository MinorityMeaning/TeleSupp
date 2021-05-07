package com.example.helloapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.helloapp.R;

public class WhatsAppFragment extends Fragment {
    public WhatsAppFragment() { }

    public static WhatsAppFragment newInstance() {
        return new WhatsAppFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_whatsapp, container, false);
    }
}
