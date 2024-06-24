package com.abdo.braintumordetection.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.braintumordetection.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerImages2Adapter extends RecyclerView.Adapter<RecyclerImages2Adapter.Holder> {


    private ArrayList<Uri> list;

    public void setList(ArrayList<Uri> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Uri u = list.get(position);
        holder.Image.setImageURI(u);

    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView Image;


        public Holder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.image);




        }
    }



}
