package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.adapter.CommentAdapter;
import com.example.btl.adapter.saveTruyenAdapter;
import com.example.btl.api.ApiComment;
import com.example.btl.api.ApiGetComment;
import com.example.btl.api.ApiclientSave;
import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.Comment;
import com.example.btl.object.saveTruyen;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {
    EditText comment_edit_text;
    Button post_comment_button;

    RecyclerView rcv_comments;

    String CommentText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        comment_edit_text = findViewById(R.id.comment_edit_text);
        post_comment_button = findViewById(R.id.post_comment_button);
        rcv_comments = findViewById(R.id.rcv_comment);
        getCommentsApi();
        setClick();
    }

    public void setClick(){
        post_comment_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommentText = comment_edit_text.getText().toString();
                Log.v("EditText",CommentText);
                comment_edit_text.setText("");
            }
        });
    }

    public void getCommentsApi(){

        Call<List<Comment>> apicall = ApiComment.getInstance().getApis().getComment(1);
        apicall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    // xử lý dữ liệu trả về khi response thành công
                    List<Comment> comments = response.body();
                    Toast.makeText(CommentActivity.this,"Got Truyen",Toast.LENGTH_SHORT).show();
                    setAdapter(comments);
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
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(CommentActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                Log.e("API Error", t.getMessage());
            }
        });


    }
    private void setAdapter(List<Comment> comments){
        rcv_comments.setLayoutManager(new LinearLayoutManager(this));
        CommentAdapter commentAdapter = new CommentAdapter(this,comments);
        rcv_comments.setAdapter(commentAdapter);
    }
}
