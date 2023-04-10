package com.example.btl.api;

import com.example.btl.object.DeleteSaveTruyen;
import com.example.btl.object.PostComment;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiDeleteSaveTruyen {
    ApiDeleteSaveTruyen api = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiDeleteSaveTruyen.class);

    @DELETE("/manga/unsave/{userId}/{commicId}")
    Call<DeleteSaveTruyen> deleteSaveTruyen(@Path("userId") int userId, @Path("commicId") int commicId);
}
