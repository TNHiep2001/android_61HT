package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.adapter.listTruyenAdapter;
import com.example.btl.api.ApiAddSaveTruyen;
import com.example.btl.api.ApiLayTruyen;
import com.example.btl.interfaces.LayTruyenVe;
import com.example.btl.object.AddSaveTruyen;
import com.example.btl.object.User;
import com.example.btl.object.listTruyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyen;

    listTruyenAdapter adapter;

    ArrayList<listTruyen> listTruyenArrayList;

    EditText edtTimKiem;

    ImageView icDangNhap;

    ImageView profile;

    ImageView savetruyen;

    int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId = getIntent().getIntExtra("userId",1);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiLayTruyen(this).execute();
    }

    private void init(){
        listTruyenArrayList = new ArrayList<>();

        adapter = new listTruyenAdapter(this, 0, listTruyenArrayList);
    }
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        icDangNhap = findViewById(R.id.icDangNhap);
        profile = findViewById(R.id.profileUser);
        savetruyen = findViewById(R.id.save_truyen);
    }
    private void setUp(){
        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClik(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                listTruyen listTruyen = listTruyenArrayList.get(i);
                Intent intent = new Intent(MainActivity.this,ChapActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("truyen",listTruyen);
                intent.putExtra("data",b);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
        icDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profile.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        savetruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Đang xử lý dữ liệu",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try{
            listTruyenArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for(int i=0; i<arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                listTruyenArrayList.add(new listTruyen(o));
            }
            adapter = new listTruyenAdapter(this, 0, listTruyenArrayList);
            gdvDSTruyen.setAdapter(adapter);
            Toast.makeText(this,"Lấy dữ liệu thành công",Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();
    }

}