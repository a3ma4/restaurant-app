package com.example.restaurantapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.restaurantapp.databinding.ActivityProductDetailBinding;

import java.util.List;

public class ProductDetailActivity extends BaseActivity  {

    ActivityProductDetailBinding binding;
    AppDatabase db;
    Product product;
    int qty = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getInstance(this);

        int productId = getIntent().getIntExtra("productId", -1);
        List<Product> all = db.productDao().getAll();
        for (Product p : all) if (p.id == productId) product = p;

        if (product == null) {
            finish();
            return;
        }

        binding.title.setText(product.name);
        binding.desc.setText(product.description);
        binding.price.setText(String.format("%.2f", product.price));

        try {
            Glide.with(this).load(product.imageUri).into(binding.image);
        } catch (Exception ignored) {}

        binding.tvQty.setText(String.valueOf(qty));

        binding.btnPlus.setOnClickListener(v -> {
            qty++;
            binding.tvQty.setText(String.valueOf(qty));
        });

        binding.btnMinus.setOnClickListener(v -> {
            if (qty > 1) qty--;
            binding.tvQty.setText(String.valueOf(qty));
        });

        binding.btnAddCart.setOnClickListener(v -> {
            db.cartDao().insert(new CartItem(product.id, product.name, product.price, product.imageUri, qty));
            Toast.makeText(this, "تم الإضافة للسلة", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
