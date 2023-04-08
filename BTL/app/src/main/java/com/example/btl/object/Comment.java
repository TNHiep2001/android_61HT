package com.example.btl.object;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("Content")
    String Content;
    @SerializedName("username")
    String userName;

    public Comment(String content) {
        this.Content = content;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return Content;
    }
}
