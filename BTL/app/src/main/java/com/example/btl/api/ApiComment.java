package com.example.btl.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiComment {
    private static ApiComment instance = null;
    private ApiGetComment apis;

    private ApiComment(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiGetComment.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apis = retrofit.create(ApiGetComment.class);
    }
    public static synchronized ApiComment getInstance(){
        if (instance == null){
            instance = new ApiComment();
        }
        return instance;
    }
    public ApiGetComment getApis(){
        return apis;
    }
}
