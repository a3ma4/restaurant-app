package com.example.restaurantapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {
    @Insert long insert(CartItem item);
    @Query("SELECT * FROM cart_items") List<CartItem> getAll();
    @Delete void delete(CartItem item);
    @Query("DELETE FROM cart_items") void clear();
    @Update void update(CartItem item);
}
