package com.example.btl.api;

import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiGetComment {
    String BASE_URL = "http://192.168.1.110:3000";

    @GET("comment/get/{idComic}")
    Call<List<Comment>> getComment(@Path("idComic") int idComic);
}
