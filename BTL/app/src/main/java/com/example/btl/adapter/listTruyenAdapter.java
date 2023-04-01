package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.R;
import com.example.btl.object.listTruyen;

import java.util.ArrayList;
import java.util.List;

public class listTruyenAdapter extends ArrayAdapter<listTruyen> {
    private Context ct;
    private ArrayList<listTruyen> arr;
    public listTruyenAdapter(Context context, int resource, List<listTruyen> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    public void sortTruyen(String s){
        s=s.toUpperCase();
        int k=0;
        for(int i=0; i<arr.size(); i++){
            listTruyen t = arr.get(i);
            String ten = t.getTenTruyen().toUpperCase();
            if(ten.indexOf(s)>=0){
                arr.set(i,arr.get(k));
                arr.set(k,t);
                k++;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen,null);
        }
        if(arr.size()>0){
            listTruyen listTruyen = this.arr.get(position);
            TextView tenTenTruyen = convertView.findViewById(R.id.txvTenTruyen);
            TextView tenTenChap = convertView.findViewById(R.id.txvTenChap);
            ImageView imgAnhtruyen = convertView.findViewById(R.id.imgAnhTruyen);
            TextView soLuongLike = convertView.findViewById(R.id.txvCountLike);
            TextView soLuongView = convertView.findViewById(R.id.txvCountView);

            tenTenTruyen.setText(listTruyen.getTenTruyen());
            tenTenChap.setText(listTruyen.getTenChap());
            Glide.with(this.ct).load(listTruyen.getUrlAnh()).into(imgAnhtruyen);
            soLuongLike.setText(listTruyen.getSLLike().toString());
            soLuongView.setText(listTruyen.getSLView().toString());
        }
        return convertView;
    }
}
