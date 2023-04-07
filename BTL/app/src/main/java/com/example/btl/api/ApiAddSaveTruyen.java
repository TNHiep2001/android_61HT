package com.example.btl.api;

import com.example.btl.object.AddSaveTruyen;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public interface ApiAddSaveTruyen {

    ApiAddSaveTruyen apiAddSaveTruyen = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiAddSaveTruyen.class);

    @POST("manga/save/1/1")
    Call<AddSaveTruyen> addSaveTruyen();
}
