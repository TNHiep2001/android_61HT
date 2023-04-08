package com.example.btl.api;

import com.example.btl.object.saveTruyen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiSaveTruyen {
    String BASE_URL = "http://192.168.1.110:3000";
    @GET("/manga/get/save/{userId}")
    Call<List<saveTruyen>> getSaveTruyen(@Path("userId")int userId);
}
