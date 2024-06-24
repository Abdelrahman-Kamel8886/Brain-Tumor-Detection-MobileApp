package com.abdo.braintumordetection.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.models.ModelUser;
import com.abdo.braintumordetection.utils.SharedModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.Holder> {


    ArrayList<ModelUser> list = new ArrayList<>();

    public void setList(ArrayList<ModelUser> list) {
        this.list = list;
    }

    private UsersRecyclerAdapter.OnItemClick onItemClick ;

    public void setOnItemClick (UsersRecyclerAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users , parent , false);

        return new UsersRecyclerAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.user.setText("Dr."+list.get(position).getName());

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView user;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user_txt);
            imageView = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){

                        onItemClick.OnClick(list.get(getLayoutPosition()).getPhone() , list.get(getLayoutPosition()).getName() , list.get(getLayoutPosition()).getImage());
                    }
                }
            });

        }
    }

    public interface OnItemClick{

        void OnClick(String phone , String name , String img);

    }
}
