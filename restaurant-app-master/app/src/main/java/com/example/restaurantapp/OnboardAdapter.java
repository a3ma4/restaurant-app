package com.example.restaurantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnboardAdapter extends RecyclerView.Adapter<OnboardAdapter.OnboardViewHolder> {

    private final String[] titles;
    private final String[] descs;
    private final int[] images;

    public OnboardAdapter(Context context) {
        titles = context.getResources().getStringArray(R.array.onboard_titles);
        descs = context.getResources().getStringArray(R.array.onboard_descs);
        images = new int[]{
                R.drawable.onboard_fast,
                R.drawable.onboard_healthy,
                R.drawable.onboard_quality
        };
    }

    @NonNull
    @Override
    public OnboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_onboard, parent, false);
        return new OnboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardViewHolder holder, int position) {
        holder.title.setText(titles[position]);
        holder.desc.setText(descs[position]);
        holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class OnboardViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;
        ImageView image;

        public OnboardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.onboard_title);
            desc = itemView.findViewById(R.id.onboard_desc);
            image = itemView.findViewById(R.id.onboard_image);
        }
    }
}
