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
import com.example.btl.api.ApiPostComment;
import com.example.btl.api.ApiclientSave;
import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.Comment;
import com.example.btl.object.PostComment;
import com.example.btl.object.User;
import com.example.btl.object.listTruyen;
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

    listTruyen listTruyen;

    Integer userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Bundle b = getIntent().getBundleExtra("data");
        listTruyen =(listTruyen) b.getSerializable("truyen");
        userId = getIntent().getIntExtra("userId",1);
        anhXa();
        getCommentsApi();
        setClick();
    }

    private void anhXa(){
        comment_edit_text = findViewById(R.id.comment_edit_text);
        post_comment_button = findViewById(R.id.post_comment_button);
        rcv_comments = findViewById(R.id.rcv_comment);
    }

    public void setClick(){
        post_comment_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postComment();
            }
        });
    }

    public void getCommentsApi(){

        Call<List<Comment>> apicall = ApiComment.getInstance().getApis().getComment(listTruyen.getIdTruyen());
        apicall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    // xử lý dữ liệu trả về khi response thành công
                    List<Comment> comments = response.body();
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
    private  void postComment(){
        CommentText = comment_edit_text.getText().toString();
        PostComment postComment = new PostComment(CommentText);
        comment_edit_text.setText("");
        ApiPostComment.apiPostComment.postComment(userId,listTruyen.getIdTruyen(),postComment).enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                Toast.makeText(CommentActivity.this,"Post thanh cong",Toast.LENGTH_SHORT).show();
                getCommentsApi();
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {
                Toast.makeText(CommentActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
            }
        });
    }
}