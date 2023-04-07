package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommentActivity extends AppCompatActivity {

    Button commentButton;
    EditText commentEditText;
    String commentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        anhXa();
        setUp();
        setClik();
    }

    private void init(){
    }
    private void anhXa(){
        commentButton = findViewById(R.id.post_comment_button);
        commentEditText = findViewById(R.id.comment_edit_text);
    }
    private void setUp(){
    }
    private void setClik() {
        commentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("EditText",commentEditText.getText().toString());
            }
        });
    }
}
