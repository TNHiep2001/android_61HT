package com.example.btl.object;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    int id;
    String username;
    String password;

    String message;

    public String getMessage() {
        return message;
    }

    public User(String username , String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
