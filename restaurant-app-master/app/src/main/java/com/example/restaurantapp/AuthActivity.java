package com.example.restaurantapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantapp.databinding.ActivityAuthBinding;

public class AuthActivity extends BaseActivity  {

    ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AuthPagerAdapter adapter = new AuthPagerAdapter(getSupportFragmentManager(), this);
        binding.viewPager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
    }
}
