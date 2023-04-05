package com.example.btl.object;


import com.google.gson.annotations.SerializedName;

public class saveTruyen {
    @SerializedName("Name")
    String Name;

    @SerializedName("Description")
    String Description;
    @SerializedName("url")
    String url;

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getUrl() {
        return url;
    }

}
