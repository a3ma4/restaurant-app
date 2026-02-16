package com.example.restaurantapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantapp.databinding.ActivityAdminBinding;

import java.util.Arrays;
import java.util.List;

public class AdminActivity extends BaseActivity  {

    private ActivityAdminBinding binding;
    private AppDatabase db;
    private Uri imageUri;

    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    imageUri = uri;
                    binding.img.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getInstance(this);

        List<String> categories = Arrays.asList("pastry", "sweet");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        binding.btnPickImage.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        binding.btnSave.setOnClickListener(v -> saveProduct());
    }

    private void saveProduct() {
        String name = binding.etName.getText().toString().trim();
        String desc = binding.etDesc.getText().toString().trim();
        String priceStr = binding.etPrice.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "املأ الحقول المطلوبة", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        String category = binding.spinner.getSelectedItem().toString();
        String image = imageUri != null ? imageUri.toString() : "";

        Product product = new Product(name, desc, price, image, category);
        db.productDao().insert(product);

        Toast.makeText(this, "تم الحفظ بنجاح", Toast.LENGTH_SHORT).show();

        clearFields();
    }

    private void clearFields() {
        binding.etName.setText("");
        binding.etDesc.setText("");
        binding.etPrice.setText("");
        binding.spinner.setSelection(0);
        binding.img.setImageResource(android.R.color.darker_gray);
        imageUri = null;
    }
}
