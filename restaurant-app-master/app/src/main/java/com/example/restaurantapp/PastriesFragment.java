package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.restaurantapp.databinding.FragmentProductsListBinding;

import java.util.List;

public class PastriesFragment extends Fragment {

    private FragmentProductsListBinding binding;
    private AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductsListBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(requireContext());

        List<Product> list = db.productDao().getByCategory("pastry");

        ProductAdapter adapter = new ProductAdapter(list, p -> {
            Intent i = new Intent(requireContext(), ProductDetailActivity.class);
            i.putExtra("productId", p.id);
            startActivity(i);
        });

        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycler.setAdapter(adapter);

        return binding.getRoot();
    }
}
