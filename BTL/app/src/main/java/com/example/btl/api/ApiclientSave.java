package com.example.btl.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiclientSave {
    private static ApiclientSave instance = null;
    private ApiSaveTruyen apis;

    private ApiclientSave(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiSaveTruyen.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apis = retrofit.create(ApiSaveTruyen.class);
    }
    public static synchronized ApiclientSave getInstance(){
        if (instance == null){
            instance = new ApiclientSave();
        }
        return instance;
    }
    public ApiSaveTruyen getApis(){
        return apis;
    }
}
