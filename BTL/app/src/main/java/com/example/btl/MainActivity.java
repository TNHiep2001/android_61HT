package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl.adapter.listTruyenAdapter;
import com.example.btl.api.ApiLayTruyen;
import com.example.btl.interfaces.LayTruyenVe;
import com.example.btl.object.listTruyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyen;

    listTruyenAdapter adapter;

    ArrayList<listTruyen> listTruyenArrayList;

    EditText edtTimKiem;

    ImageView icDangNhap;

    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Bundle b = new Bundle();
                b.putSerializable("truyen",listTruyen);
                Intent intent = new Intent(MainActivity.this,ChapActivity.class);
                intent.putExtra("data",b);
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
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Dang xu ly du lieu",Toast.LENGTH_SHORT);
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
        }
        catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi ket noi",Toast.LENGTH_SHORT);
    }
}