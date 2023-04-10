package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.api.ApiPostComment;
import com.example.btl.api.ApiSigup;
import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.Comment;
import com.example.btl.object.PostComment;
import com.example.btl.object.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {

    Button dkdangnhap, dkdangky;
    EditText edtUser,edtPass;

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
        edtUser = findViewById(R.id.dktaikhoan);
        edtPass = findViewById(R.id.dkmatkhau);
    }
    private void setUp(){
    }

    private void setClik() {
        dkdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigup();
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

    private  void sigup(){
        String dataUser = edtUser.getText().toString();
        String dataPass = edtPass.getText().toString();
        User user = new User(dataUser,dataPass);
        ApiSigup.api.sigup(user).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 400){
                    Toast.makeText(DangKyActivity.this,"Tai khoan da ton tai",Toast.LENGTH_SHORT).show();
                }
                if (response.code()==200){
                    Toast.makeText(DangKyActivity.this,"Dang ky thanh cong",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },2000);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(DangKyActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
            }
        });
    }
}