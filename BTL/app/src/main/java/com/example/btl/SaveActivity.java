package com.example.btl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl.api.ApiAddSaveTruyen;
import com.example.btl.api.ApiDeleteSaveTruyen;
import com.example.btl.api.ApiclientSave;
import com.example.btl.adapter.saveTruyenAdapter;
import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.DeleteSaveTruyen;
import com.example.btl.object.listTruyen;
import com.example.btl.object.saveTruyen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveActivity extends AppCompatActivity {
    RecyclerView rcv_saveTruyen;
    ImageView img_back;

    List<saveTruyen> saveTruyens;

    saveTruyenAdapter SaveTruyenApdater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        rcv_saveTruyen = findViewById(R.id.rcv_saveTruyen);
        img_back = findViewById(R.id.img_back);
        getApi();
        setClik();
    }
    public void getApi(){
        Call<List<saveTruyen>> apicall = ApiclientSave.getInstance().getApis().getSaveTruyen(1);
        apicall.enqueue(new Callback<List<saveTruyen>>() {
            @Override
            public void onResponse(Call<List<saveTruyen>> call, Response<List<saveTruyen>> response) {
                if (response.isSuccessful()) {
                    // xử lý dữ liệu trả về khi response thành công
                    saveTruyens = response.body();
                    Toast.makeText(SaveActivity.this,"Got Truyen",Toast.LENGTH_SHORT).show();
                    setAdapter(saveTruyens);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API Error", errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<saveTruyen>> call, Throwable t) {
                Toast.makeText(SaveActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                Log.e("API Error", t.getMessage());
            }
        });
    }

    private void setAdapter(List<saveTruyen> saveTruyens){
        rcv_saveTruyen.setLayoutManager(new LinearLayoutManager(this));
        SaveTruyenApdater = new saveTruyenAdapter(this,saveTruyens);
        rcv_saveTruyen.setAdapter(SaveTruyenApdater);
    }
    private void setClik(){
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rcv_saveTruyen.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if (child != null) {
                        int position = rv.getChildAdapterPosition(child);
                        saveTruyen data = saveTruyens.get(position);

                        ApiDeleteSaveTruyen.api.deleteSaveTruyen(data.getUserId(),data.getCommicId()).enqueue(new Callback<DeleteSaveTruyen>() {
                            @Override
                            public void onResponse(Call<DeleteSaveTruyen> call, Response<DeleteSaveTruyen> response) {
                                Toast.makeText(SaveActivity.this,"Delete thanh cong",Toast.LENGTH_SHORT).show();
                                getApi();
                            }

                            @Override
                            public void onFailure(Call<DeleteSaveTruyen> call, Throwable t) {
                                Toast.makeText(SaveActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                return super.onInterceptTouchEvent(rv, e);
            }
        });

    }
}