package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.btl.adapter.listTruyenAdapter;

import java.util.ArrayList;

public class QuenMatKhauActivity extends AppCompatActivity {

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
    }

    private void init(){
    }
    private void anhXa(){
        back = findViewById(R.id.bt_back);
    }
    private void setUp(){
    }
    private void setClick(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuenMatKhauActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
}