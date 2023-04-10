package com.example.btl.api;

import com.example.btl.object.PostComment;
import com.example.btl.object.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiSigup {
    ApiSigup api = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiSigup.class);

    @POST("signup")
    Call<List<User>> sigup(@Body User user);

    @POST("login")
    Call<User> login(@Body User user);
}
