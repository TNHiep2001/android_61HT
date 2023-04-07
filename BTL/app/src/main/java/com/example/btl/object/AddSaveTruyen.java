package com.example.btl.object;

import com.google.gson.annotations.SerializedName;

public class AddSaveTruyen {
    @SerializedName("message")
    String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
