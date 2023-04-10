package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.adapter.listTruyenAdapter;
import com.example.btl.api.ApiLayTruyen;
import com.example.btl.api.ApiSigup;
import com.example.btl.object.Comment;
import com.example.btl.object.User;
import com.example.btl.object.listTruyen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    Button dangnhap, dangky;
    EditText dkdangnhap,dkpassword;
    TextView qmk;
    User users;

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
        dkdangnhap = findViewById(R.id.dkdangnhap);
        dkpassword = findViewById(R.id.dkpassword);
        qmk = findViewById(R.id.dkquenmatkhau);
    }
    private void setUp(){
    }
    private void setClik() {
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });

        qmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this,QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });
    }
    private  void login(){
        String dataUser = dkdangnhap.getText().toString();
        String dataPass = dkpassword.getText().toString();
        User user = new User(dataUser,dataPass);
        ApiSigup.api.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 400){
                    Toast.makeText(DangNhapActivity.this,"Tai khoan hoac mat khau khong chinh xac",Toast.LENGTH_SHORT).show();
                }
                if (response.code()==200){
                    Toast.makeText(DangNhapActivity.this,"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            users = response.body();
                            Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                            int userId = users.getId();
                            intent.putExtra("userId",userId);
                            startActivity(intent);
                            finish();
                        }
                    },2000);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(DangNhapActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                Log.e("API Error", t.getMessage());
            }
        });
    }
}