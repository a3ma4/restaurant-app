package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.restaurantapp.databinding.ActivityOnboardingBinding;

public class OnboardingActivity extends BaseActivity {

    ActivityOnboardingBinding binding;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(this);

        if (session.isOnboardingSeen()) {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
            return;
        }

        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        OnboardAdapter adapter = new OnboardAdapter(this);
        binding.viewPager.setAdapter(adapter);
        binding.tabDots.setViewPager2(binding.viewPager);

        binding.btnSkip.setOnClickListener(v -> {
            session.setOnboardingSeen(true);
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        });
    }
}
