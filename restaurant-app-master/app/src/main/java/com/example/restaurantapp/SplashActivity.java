package com.example.restaurantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

            boolean seenOnboarding = prefs.getBoolean("seen_onboarding", false);
            boolean loggedIn = prefs.getBoolean("is_logged_in", false);

            Intent intent;
            if (!seenOnboarding) {
                intent = new Intent(SplashActivity.this, OnboardingActivity.class);
            } else {
                if (loggedIn) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, AuthActivity.class);
                }
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
