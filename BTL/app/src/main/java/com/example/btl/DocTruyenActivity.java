package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.adapter.listTruyenAdapter;
import com.example.btl.api.ApiLayTruyen;
import com.example.btl.object.listTruyen;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity {
    ImageView imgAnh;
    TextView txvSoTrang;
    ArrayList<String> arrUrlAnh;
    int soTrang, soTrangDangDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        setClik();
    }

    private void init(){
        arrUrlAnh = new ArrayList<>();
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/1-4.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/2-1.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/3.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/4.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/5.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/6.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/7.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/8.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/9.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/10.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/11.png");
        arrUrlAnh.add("https://www.mattrancantho.vn/files/images/2022/12.png");
        soTrangDangDoc = 1;
        soTrang = arrUrlAnh.size();
    }
    private void anhXa(){
        imgAnh = findViewById(R.id.imgAnh);
        txvSoTrang = findViewById(R.id.txvSoTrang);
    }
    private void setUp(){
        docTheoTrang(0);
    }
    private void setClik(){

    }

    public void right(View view) {
        docTheoTrang(1);
    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    private void docTheoTrang(int i){
       soTrangDangDoc = soTrangDangDoc+i;
       if(soTrangDangDoc==0){
           soTrangDangDoc=1;
       }
       if(soTrangDangDoc>soTrang){
           soTrangDangDoc=soTrang;
       }
       txvSoTrang.setText(soTrangDangDoc+" / "+soTrang);
       Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc-1)).into(imgAnh);
    }
}