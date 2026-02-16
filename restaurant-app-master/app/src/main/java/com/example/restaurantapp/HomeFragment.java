package com.example.restaurantapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.restaurantapp.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        HomePagerAdapter adapter = new HomePagerAdapter(this);
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabs, binding.viewPager,
                (tab, pos) -> tab.setText(pos == 0
                        ? getString(R.string.tab_pastries)
                        : getString(R.string.tab_sweets))
        ).attach();

        return binding.getRoot();
    }
}
