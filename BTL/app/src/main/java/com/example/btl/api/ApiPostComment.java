package com.example.btl.api;

import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.Comment;
import com.example.btl.object.PostComment;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiPostComment {
    ApiPostComment apiPostComment = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPostComment.class);

    @POST("/comment/{userId}/{commicId}")
    Call<PostComment> postComment(@Path("userId") int userId, @Path("commicId") int commicId, @Body PostComment postComment);
}
