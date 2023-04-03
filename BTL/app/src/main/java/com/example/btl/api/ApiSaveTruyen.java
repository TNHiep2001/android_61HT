package com.example.btl.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiSaveTruyen {

    Gson gson = new GsonBuilder()
            .setDateFormat("YYYY-MM-dd HH:mm:ss")
            .create();

    ApiSaveTruyen apiSaveTruyen = new Retrofit.Builder()
            .baseUrl("64248e979e0a30d92b1ea760.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiSaveTruyen.class);

}
