package com.abdo.braintumordetection.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.models.TitleModel;

import java.util.ArrayList;

public class ReportsRecyclerAdapter extends RecyclerView.Adapter<ReportsRecyclerAdapter.Holder> {


    ArrayList<TitleModel> list = new ArrayList<>();

    public void setList(ArrayList<TitleModel> list) {
        this.list = list;
    }

    private ReportsRecyclerAdapter.OnItemClick onItemClick ;

    public void setOnItemClick (ReportsRecyclerAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report , parent , false);

        return new ReportsRecyclerAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.des.setText(list.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView title , des;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_txt);
            des = itemView.findViewById(R.id.description_txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){

                        onItemClick.OnClick(list.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public interface OnItemClick{

        void OnClick(TitleModel titleModel);

    }
}
