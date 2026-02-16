package com.example.restaurantapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.restaurantapp.databinding.FragmentOrdersBinding;

import java.util.List;

public class OrdersFragment extends Fragment {

    FragmentOrdersBinding binding;
    AppDatabase db;
    OrdersAdapter adapter;
    SessionManager session;
    List<OrderEntity> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(requireContext());
        session = new SessionManager(requireContext());

        String email = session.getUserEmail();
        list = db.orderDao().getByUser(email);

        adapter = new OrdersAdapter(list);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycler.setAdapter(adapter);

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int b, int c) {}
            @Override public void onTextChanged(CharSequence s, int st, int b, int c) {
                adapter.filter(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        return binding.getRoot();
    }
}
