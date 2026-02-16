package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.restaurantapp.databinding.FragmentCartBinding;

import java.util.List;

public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(requireContext());

        loadCart();

        binding.btnCheckout.setOnClickListener(v -> {
            if (db.cartDao().getAll().isEmpty()) {
                Toast.makeText(requireContext(), "السلة فارغة", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(requireContext(), CheckoutActivity.class));
        });

        return binding.getRoot();
    }

    private void loadCart() {
        List<CartItem> items = db.cartDao().getAll();
        CartAdapter adapter = new CartAdapter(items,
                item -> { db.cartDao().delete(item); loadCart(); },
                item -> { db.cartDao().update(item); loadCart(); });

        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycler.setAdapter(adapter);

        double total = 0;
        for (CartItem c : items) total += c.productPrice * c.quantity;
        binding.tvTotal.setText(String.format("%.2f", total));
    }
}
