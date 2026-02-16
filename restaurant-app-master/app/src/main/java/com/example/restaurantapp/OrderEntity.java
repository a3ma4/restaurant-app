package com.example.restaurantapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class OrderEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String itemsJson;
    public double total;
    public long dateMillis;

    public String userEmail;

    public OrderEntity(String itemsJson, double total, long dateMillis, String userEmail) {
        this.itemsJson = itemsJson;
        this.total = total;
        this.dateMillis = dateMillis;
        this.userEmail = userEmail;
    }
}
