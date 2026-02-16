package com.example.restaurantapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurantapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    AppDatabase db;
    SessionManager session;
    User me;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(requireContext());
        session = new SessionManager(requireContext());

        String email = session.getUserEmail();
        if (email != null) {
            me = db.userDao().getByEmail(email);
            if (me != null) {
                binding.tvName.setText(me.name);
                binding.tvPhone.setText(me.phone);
                binding.tvEmail.setText(me.email);
            }
        }

        binding.btnEdit.setOnClickListener(v -> showEditDialog());

        binding.btnLogout.setOnClickListener(v -> {
            session.clear();
            Intent i = new Intent(requireContext(), AuthActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });

        return binding.getRoot();
    }

    private void showEditDialog() {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit_profile, null);

        EditText etName = view.findViewById(R.id.etName);
        EditText etPhone = view.findViewById(R.id.etPhone);
        EditText etEmail = view.findViewById(R.id.etEmail);
        EditText etPassword = view.findViewById(R.id.etPassword);

        etName.setText(me.name);
        etPhone.setText(me.phone);
        etEmail.setText(me.email);
        etPassword.setText(me.password);

        new AlertDialog.Builder(requireContext())
                .setTitle("Edit Profile")
                .setView(view)
                .setPositiveButton("Save", (d, w) -> {
                    me.name = etName.getText().toString();
                    me.phone = etPhone.getText().toString();
                    me.email = etEmail.getText().toString();
                    me.password = etPassword.getText().toString();

                    db.userDao().update(me);

                    binding.tvName.setText(me.name);
                    binding.tvPhone.setText(me.phone);
                    binding.tvEmail.setText(me.email);

                    Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
