package com.example.helloapp;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.helloapp.fragments.WhatsAppFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    MenuListener(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    private MainActivity mainActivity;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_whatsapp:
                loadFragment(WhatsAppFragment.newInstance());
                return true;
            case R.id.navigation_telegram:
                loadFragment(WhatsAppFragment.newInstance());
                return true;
            case R.id.navigation_history:
                loadFragment(WhatsAppFragment.newInstance());
                return true;
            case R.id.navigation_setting:
                loadFragment(WhatsAppFragment.newInstance());
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }


}
