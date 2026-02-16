package com.example.restaurantapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.databinding.ItemOrderBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.VH> {

    List<OrderEntity> original;
    List<OrderEntity> filtered;

    public OrdersAdapter(List<OrderEntity> list) {
        this.original = list;
        this.filtered = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        OrderEntity o = filtered.get(position);

        StringBuilder productsText = new StringBuilder();
        try {
            JSONArray arr = new JSONArray(o.itemsJson);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                double price = item.getDouble("price");
                int qty = item.getInt("qty");

                productsText.append("• ")
                        .append(name)
                        .append(" x")
                        .append(qty)
                        .append(" = ")
                        .append(price * qty)
                        .append(" شيكل\n");
            }
        } catch (Exception e) {
            productsText.append("خطأ في قراءة الطلب");
        }

        holder.binding.tvProducts.setText(productsText.toString().trim());
        holder.binding.tvTotal.setText("الإجمالي: " + String.format("%.2f", o.total) + " شيكل");
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    public void filter(String q) {
        filtered.clear();
        if (q == null || q.trim().isEmpty()) filtered.addAll(original);
        else {
            for (OrderEntity o : original)
                if (o.itemsJson.contains(q)) filtered.add(o);
        }
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        ItemOrderBinding binding;
        VH(ItemOrderBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
