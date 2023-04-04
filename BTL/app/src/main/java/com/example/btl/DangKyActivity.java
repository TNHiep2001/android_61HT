package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {

    Button dkdangnhap, dkdangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        init();
        anhXa();
        setUp();
        setClik();
    }

    private void init(){
    }
    private void anhXa(){
        dkdangnhap = findViewById(R.id.dkdangnhap);
        dkdangky = findViewById(R.id.dkdangky);
    }
    private void setUp(){
    }
    private void infoMessage(){
        Toast.makeText(this,"Dang ky thanh cong",Toast.LENGTH_SHORT).show();}
    private void setClik() {
        dkdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoMessage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });

        dkdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}