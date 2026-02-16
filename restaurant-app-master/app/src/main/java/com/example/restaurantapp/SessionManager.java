package com.example.restaurantapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF = "app_pref";

    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_ONBOARDING = "onboarding_seen";

    private final SharedPreferences prefs;

    public SessionManager(Context ctx) {
        prefs = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public void saveUserEmail(String email) {
        prefs.edit().putString(KEY_USER_EMAIL, email).apply();
    }

    public String getUserEmail() {
        return prefs.getString(KEY_USER_EMAIL, null);
    }

    public void clear() {
        prefs.edit().clear().apply();
    }

    public void setOnboardingSeen(boolean seen) {
        prefs.edit().putBoolean(KEY_ONBOARDING, seen).apply();
    }

    public boolean isOnboardingSeen() {
        return prefs.getBoolean(KEY_ONBOARDING, false);
    }
}
