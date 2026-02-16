package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restaurantapp.databinding.ActivityAdminLoginBinding;

public class AdminLoginActivity extends BaseActivity  {

    ActivityAdminLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdminLogin.setOnClickListener(v -> {
            String ip = binding.etIp.getText().toString().trim();

            if (ip.equals("admin123")) {
                startActivity(new Intent(this, AdminActivity.class));
                finish();
            } else {
                Toast.makeText(this, "IP غير صحيح", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
