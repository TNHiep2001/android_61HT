package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.adapter.ChapTruyenAdapter;
import com.example.btl.object.ChapTruyen;
import com.example.btl.object.listTruyen;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity {
    TextView txvTenTruyens; TextView txvCountLikeChap; TextView txvCountViewChap;
    ImageView imgAnhTruyens;
    ImageView imgComment;
    listTruyen listTruyen;
    ListView lsvDanhSachChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;

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
    }
    private void setUp(){
        txvTenTruyens.setText(listTruyen.getTenTruyen());
        Glide.with(this).load(listTruyen.getUrlAnh()).into(imgAnhTruyens);

        lsvDanhSachChap.setAdapter(chapTruyenAdapter);

        txvCountLikeChap.setText(listTruyen.getSLLike().toString());
        txvCountViewChap.setText(listTruyen.getSLView().toString());
    }
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
                startActivity(intent);
            }
        });
    }
}