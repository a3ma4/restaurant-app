package com.example.restaurantapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String phone;
    public String email;
    public String password;
    public boolean isAdmin;

    public User(String name, String phone, String email, String password, boolean isAdmin) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
