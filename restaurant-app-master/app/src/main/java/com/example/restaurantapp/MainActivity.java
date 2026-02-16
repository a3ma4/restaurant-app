package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.restaurantapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity
        implements com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.setLocale(this, LocaleHelper.getSavedLanguage(this));

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = resourceId > 0 ? getResources().getDimensionPixelSize(resourceId) : 0;

        binding.toolbar.setPadding(
                binding.toolbar.getPaddingLeft(),
                statusBarHeight / 3,
                binding.toolbar.getPaddingRight(),
                binding.toolbar.getPaddingBottom()
        );

        binding.bottomNav.setItemIconTintList(null);
        binding.navView.setItemIconTintList(null);

        binding.bottomNav.setItemTextColor(
                ContextCompat.getColorStateList(this, R.color.nav_item_color)
        );

        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.open_nav,
                R.string.close_nav
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);
        binding.bottomNav.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.fragmentContainer.getId(), new HomeFragment())
                    .commit();
            binding.bottomNav.setSelectedItemId(R.id.menu_home);
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d(TAG, "OnBackPressedCallback called");

                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    return;
                }

                int selectedId = binding.bottomNav.getSelectedItemId();

                if (selectedId != R.id.menu_home) {
                    binding.bottomNav.setSelectedItemId(R.id.menu_home);
                    getSupportFragmentManager().beginTransaction()
                            .replace(binding.fragmentContainer.getId(), new HomeFragment())
                            .commit();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            selectedFragment = new HomeFragment();
        } else if (id == R.id.menu_orders) {
            selectedFragment = new OrdersFragment();
        } else if (id == R.id.menu_cart) {
            selectedFragment = new CartFragment();
        } else if (id == R.id.menu_profile) {
            selectedFragment = new ProfileFragment();
        }

        else if (id == R.id.nav_profile) {
            binding.bottomNav.setSelectedItemId(R.id.menu_profile);
            selectedFragment = new ProfileFragment();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, getString(R.string.nav_logout), Toast.LENGTH_SHORT).show();
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putBoolean("is_logged_in", false)
                    .apply();
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        } else if (id == R.id.nav_language) {
            String currentLang = LocaleHelper.getSavedLanguage(this);
            String nextLang = currentLang.equals("ar") ? "en" : "ar";
            LocaleHelper.setLocale(this, nextLang);

            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        if (selectedFragment != null) {
            Fragment current = getSupportFragmentManager().findFragmentById(binding.fragmentContainer.getId());
            if (current == null || !current.getClass().equals(selectedFragment.getClass())) {
                getSupportFragmentManager().beginTransaction()
                        .replace(binding.fragmentContainer.getId(), selectedFragment)
                        .commit();
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
