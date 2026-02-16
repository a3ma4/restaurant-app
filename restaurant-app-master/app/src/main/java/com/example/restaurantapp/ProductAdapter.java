package com.example.restaurantapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantapp.databinding.ItemProductBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    public interface Callback {
        void onClick(Product p);
    }

    private final List<Product> list;
    private final Callback cb;

    public ProductAdapter(List<Product> list, Callback cb) {
        this.list = list;
        this.cb = cb;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Product p = list.get(position);

        holder.binding.title.setText(p.name);
        holder.binding.desc.setText(p.description);
        holder.binding.price.setText(String.format("%.2f", p.price));

        try {
            Glide.with(holder.binding.image.getContext()).load(p.imageUri).into(holder.binding.image);
        } catch (Exception ignored) {}

        holder.binding.getRoot().setOnClickListener(v -> cb.onClick(p));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ItemProductBinding binding;
        VH(ItemProductBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
