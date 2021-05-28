package com.mardaunt.telesupp;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mardaunt.telesupp.fragments.DevelopFragment;
import com.mardaunt.telesupp.fragments.SettingFragment;
import com.mardaunt.telesupp.fragments.WhatsAppFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MainActivity mainActivity;

    public MenuListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_whatsapp:
                loadFragment(WhatsAppFragment.newInstance());
                return true;
            case R.id.navigation_telegram:
                loadFragment(DevelopFragment.newInstance(null, null));
                return true;
            case R.id.navigation_history:
                loadFragment(DevelopFragment.newInstance(null, null));
                return true;
            case R.id.navigation_setting:
                loadFragment(SettingFragment.newInstance(null, null));
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
