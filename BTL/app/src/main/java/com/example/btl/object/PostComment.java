package com.example.btl.object;

import com.google.gson.annotations.SerializedName;

public class PostComment {


    @SerializedName("Content")

    String comment;

    public PostComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
