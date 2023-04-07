package com.example.btl.object;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("Content")
    String Content;


    public String getContent() {
        return Content;
    }
}
