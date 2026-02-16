package com.example.restaurantapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantapp.databinding.ActivityCheckoutBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CheckoutActivity extends BaseActivity  {

    ActivityCheckoutBinding binding;
    AppDatabase db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AppDatabase.getInstance(this);
        session = new SessionManager(this);

        loadSummary();

        binding.btnPay.setOnClickListener(v -> {
            if (binding.rbCard.isChecked()) {
                String card = binding.etCardNumber.getText().toString();
                if (card.length() < 12) {
                    Toast.makeText(this, "ادخل بيانات البطاقة", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            new AlertDialog.Builder(this)
                    .setTitle("تأكيد")
                    .setMessage("تأكيد الدفع؟")
                    .setPositiveButton("نعم", (d, w) -> {
                        List<CartItem> items = db.cartDao().getAll();
                        double total = 0;
                        JSONArray arr = new JSONArray();

                        for (CartItem it : items) {
                            try {
                                JSONObject o = new JSONObject();
                                o.put("name", it.productName);
                                o.put("price", it.productPrice);
                                o.put("qty", it.quantity);
                                arr.put(o);
                                total += it.productPrice * it.quantity;
                            } catch (Exception e) {}
                        }

                        String email = session.getUserEmail();
                        db.orderDao().insert(new OrderEntity(arr.toString(), total, System.currentTimeMillis(), email));
                        db.cartDao().clear();

                        Toast.makeText(this, "تم الدفع. شكراً!", Toast.LENGTH_LONG).show();
                        finish();
                    })
                    .setNegativeButton("لا", null)
                    .show();
        });
    }

    private void loadSummary() {
        List<CartItem> items = db.cartDao().getAll();
        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (CartItem it : items) {
            sb.append(it.productName).append(" x").append(it.quantity).append("\n");
            total += it.productPrice * it.quantity;
        }

        binding.tvItems.setText(sb.toString());
        binding.tvTotal.setText(String.format("%.2f", total));
    }
}
