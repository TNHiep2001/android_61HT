package com.example.btl.api;

import com.example.btl.interfaces.User;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiUser {
    String data;

    User user;

    public ApiUser(User user){
        this.user = user;

    }
    @Override
    protected  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://642be8c9208dfe2547229a79.mockapi.io/api/dangnhap/dangnhap")
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }
        catch (IOException e){
            data = null;
        }
        return null;
    }
}
