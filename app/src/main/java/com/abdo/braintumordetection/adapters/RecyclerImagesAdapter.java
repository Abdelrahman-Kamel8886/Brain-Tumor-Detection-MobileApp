package com.abdo.braintumordetection.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.braintumordetection.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerImagesAdapter extends RecyclerView.Adapter<RecyclerImagesAdapter.Holder> {


    private ArrayList<String> list;

    public void setList(ArrayList<String> list) {
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
        String url = list.get(position);

        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.Image);

    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView Image;


        public Holder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.image);




        }
    }



}
