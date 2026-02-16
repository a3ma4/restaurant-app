package com.example.restaurantapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert long insert(Product product);
    @Query("SELECT * FROM products WHERE category = :category") List<Product> getByCategory(String category);
    @Query("SELECT * FROM products") List<Product> getAll();
}
