package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurantapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    AppDatabase db;
    SessionManager session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(requireContext());
        session = new SessionManager(requireContext());

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String pass = binding.etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(requireContext(), "املأ الحقول", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = db.userDao().login(email, pass);
            if (user != null) {
                session.saveUserEmail(user.email);
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();
            } else {
                Toast.makeText(requireContext(), "بيانات غير صحيحة", Toast.LENGTH_SHORT).show();
            }
        });

        binding.image.setOnLongClickListener(v -> {
            startActivity(new Intent(requireContext(), AdminLoginActivity.class));
            return true;
        });


        return binding.getRoot();
    }
}
