package com.example.restaurantapp;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AuthPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    public AuthPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return position == 0 ? new LoginFragment() : new RegisterFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0
                ? context.getString(R.string.tab_login)
                : context.getString(R.string.tab_register);
    }
}
