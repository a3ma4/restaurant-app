package com.example.restaurantapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurantapp.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(requireContext());

        binding.btnRegister.setOnClickListener(v -> {
            String name = binding.etName.getText().toString().trim();
            String phone = binding.etPhone.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String pass = binding.etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(requireContext(), "املأ الحقول", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.userDao().getByEmail(email) != null) {
                Toast.makeText(requireContext(), "البريد مسجل", Toast.LENGTH_SHORT).show();
                return;
            }

            db.userDao().insert(new User(name, phone, email, pass, false));
            Toast.makeText(requireContext(), "تم التسجيل", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }
}
