package com.example.restaurantapp;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LocaleHelper.setLocale(getApplicationContext(), LocaleHelper.getSavedLanguage(getApplicationContext()));
    }
}
