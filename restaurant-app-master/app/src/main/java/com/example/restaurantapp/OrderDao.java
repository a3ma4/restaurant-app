package com.example.restaurantapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert long insert(OrderEntity order);

    @Query("SELECT * FROM orders WHERE userEmail = :email ORDER BY dateMillis DESC")
    List<OrderEntity> getByUser(String email);

    @Query("SELECT * FROM orders ORDER BY dateMillis DESC")
    List<OrderEntity> getAll();
}
