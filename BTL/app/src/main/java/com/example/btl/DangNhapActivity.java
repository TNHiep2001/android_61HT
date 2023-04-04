package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.btl.adapter.listTruyenAdapter;
import com.example.btl.api.ApiLayTruyen;
import com.example.btl.object.listTruyen;

import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity {
    Button dangnhap, dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        init();
        anhXa();
        setUp();
        setClik();
    }

    private void init(){
    }
    private void anhXa(){
        dangnhap = findViewById(R.id.dangnhap);
        dangky = findViewById(R.id.dangky);
    }
    private void setUp(){
    }
    private void infoMessage(){Toast.makeText(this,"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();}
    private void setClik() {
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoMessage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}