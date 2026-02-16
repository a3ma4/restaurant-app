package com.example.restaurantapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String description;
    public double price;
    public String imageUri;
    public String category;

    public Product(String name, String description, double price, String imageUri, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUri = imageUri;
        this.category = category;
    }
}
