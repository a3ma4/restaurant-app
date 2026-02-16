package com.example.restaurantapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantapp.databinding.ItemCartBinding;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VH> {

    public interface OnDelete {
        void delete(CartItem item);
    }

    public interface OnChange {
        void changed(CartItem item);
    }

    private final List<CartItem> list;
    private final OnDelete del;
    private final OnChange change;

    public CartAdapter(List<CartItem> list, OnDelete del, OnChange change) {
        this.list = list;
        this.del = del;
        this.change = change;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        CartItem c = list.get(position);

        holder.binding.name.setText(c.productName);
        holder.binding.price.setText(String.format("%.2f", c.productPrice));
        holder.binding.qty.setText(String.valueOf(c.quantity));

        if (c.productImage != null && !c.productImage.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(c.productImage)
                    .into(holder.binding.image);
        } else {
            holder.binding.image.setImageResource(R.mipmap.ic_launcher);
        }

        holder.binding.btnDelete.setOnClickListener(v -> del.delete(c));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ItemCartBinding binding;

        VH(ItemCartBinding b) {
            super(b.getRoot());
            this.binding = b;
        }
    }
}
