package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.btl.adapter.ChapTruyenAdapter;
import com.example.btl.api.ApiAddSaveTruyen;
import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.ChapTruyen;
import com.example.btl.object.listTruyen;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapActivity extends AppCompatActivity {
    TextView txvTenTruyens; TextView txvCountLikeChap; TextView txvCountViewChap;
    ImageView imgAnhTruyens; ImageView imglike;
    ImageView imgComment;
    listTruyen listTruyen;
    ListView lsvDanhSachChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;

    ImageView imgsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        listTruyen =(listTruyen) b.getSerializable("truyen");

        arrChap = new ArrayList<>();
        for(int i=1; i<=(listTruyen.getSlChap()); i++){
            arrChap.add(new ChapTruyen("Chapter " + i,listTruyen.getNgayDang()));
        }
        chapTruyenAdapter = new ChapTruyenAdapter(this,0,arrChap);
    }
    private void anhXa(){
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
        txvCountLikeChap = findViewById(R.id.txvCountLikeChap);
        txvCountViewChap = findViewById(R.id.txvCountViewChap);
        imgComment = findViewById(R.id.imgComment);
        imglike = findViewById(R.id.imglike);
        imgsave = findViewById(R.id.img_save);
    }
    private void setUp(){
        txvTenTruyens.setText(listTruyen.getTenTruyen());
        Glide.with(this).load(listTruyen.getUrlAnh()).into(imgAnhTruyens);

        lsvDanhSachChap.setAdapter(chapTruyenAdapter);

        txvCountLikeChap.setText(listTruyen.getSLLike().toString());
        txvCountViewChap.setText(listTruyen.getSLView().toString());
    }
    private void infoMessage(){
        Toast.makeText(this,"Bạn đã thích bộ truyện này",Toast.LENGTH_SHORT).show();}
    private void setClick(){
        lsvDanhSachChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
                startActivity(intent);
            }
        });

        imgComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChapActivity.this, CommentActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("truyen",listTruyen);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });

        imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imglike.setColorFilter(Color.rgb(243,83,105));
                txvCountLikeChap.setText(Integer.toString((listTruyen.getSLLike() + 1)));
                infoMessage();
            }
        });

        imgsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiAddSaveTruyen.apiAddSaveTruyen.addSaveTruyen(listTruyen.getIdUser(),listTruyen.getIdTruyen()).enqueue(new Callback<AddSaveTruyen>() {
                    @Override
                    public void onResponse(Call<AddSaveTruyen> call, Response<AddSaveTruyen> response) {
                        // xử lý dữ liệu trả về khi response thành công
                        if (response.code() == 401){
                            Toast.makeText(ChapActivity.this,"Truyện đã được save",Toast.LENGTH_SHORT).show();
                        }
                        if (response.code()==200){
                            AddSaveTruyen addSaveTruyens = response.body();
                            String b = addSaveTruyens.getMessage();
                            Toast.makeText(ChapActivity.this,b,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddSaveTruyen> call, Throwable t) {
                        Toast.makeText(ChapActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                        Log.e("API Error", t.getMessage());
                    }
                });
            }
        });
    }
}