package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile extends AppCompatActivity {
    Button backToHomePage;
    Integer userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userId = getIntent().getIntExtra("userId",1);
        init();
        anhXa();
        setUp();
        setClik();
    }

    private void init(){
    }
    private void anhXa(){
        backToHomePage = findViewById(R.id.back_to_hompage_button);
    }
    private void setUp(){
    }
    private void setClik() {
        backToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this,MainActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }
}