package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl.R;
import com.example.btl.object.saveTruyen;

import java.util.ArrayList;
import java.util.List;

public class saveTruyenAdapter extends RecyclerView.Adapter<saveTruyenAdapter.ViewHolder> {

    Context mcontext;
    List<saveTruyen> saveTruyens;
    public saveTruyenAdapter(Context context, List<saveTruyen> saveTruyens) {
        this.saveTruyens = saveTruyens;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public saveTruyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_save_truyen,parent,false);
        return new saveTruyenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull saveTruyenAdapter.ViewHolder holder, int position) {
        holder.name.setText(saveTruyens.get(position).getName());
        holder.mota.setText(saveTruyens.get(position).getDescription());
        Glide.with(mcontext).load(saveTruyens.get(position).getUrl()).placeholder(R.drawable.t1).error(R.drawable.ic_launcher_background).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return saveTruyens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,mota;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txv_name);
            mota = itemView.findViewById(R.id.txv_mota);
            image = itemView.findViewById(R.id.imgAnhTruyen);
        }
    }
}
