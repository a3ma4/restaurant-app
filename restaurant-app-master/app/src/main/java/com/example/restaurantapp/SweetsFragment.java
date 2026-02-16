package com.example.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.restaurantapp.databinding.FragmentProductsListBinding;

import java.util.List;


public class SweetsFragment extends Fragment {

    private FragmentProductsListBinding binding;
    private AppDatabase db;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductsListBinding.inflate(inflater, container, false);

        Context context = requireContext();


        db = AppDatabase.getInstance(context);

        List<Product> productList = db.productDao().getByCategory("sweet");

        adapter = new ProductAdapter(productList, product -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productId", product.id);
            startActivity(intent);
        });

        binding.recycler.setLayoutManager(new LinearLayoutManager(context));
        binding.recycler.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.recycler.setAdapter(null);
        binding = null;
    }

}