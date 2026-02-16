package com.example.restaurantapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int productId;
    public String productName;
    public double productPrice;
    public String productImage;
    public int quantity;

    public CartItem(int productId, String productName, double productPrice, String productImage, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.quantity = quantity;
    }
}
