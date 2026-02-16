package com.example.restaurantapp;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = LocaleHelper.getSavedLanguage(newBase);
        Context context = LocaleHelper.setLocale(newBase, lang);
        super.attachBaseContext(context);
    }
}
