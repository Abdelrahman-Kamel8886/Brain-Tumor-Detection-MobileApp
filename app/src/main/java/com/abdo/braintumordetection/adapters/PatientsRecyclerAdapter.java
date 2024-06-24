package com.abdo.braintumordetection.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.braintumordetection.R;
import com.abdo.braintumordetection.models.ModelPatient;


import java.util.ArrayList;

public class PatientsRecyclerAdapter extends RecyclerView.Adapter<PatientsRecyclerAdapter.Holder> {


    ArrayList<ModelPatient> list = new ArrayList<>();

    public void setList(ArrayList<ModelPatient> list) {
        this.list = list;
    }

    private PatientsRecyclerAdapter.OnItemClick onItemClick ;

    public void setOnItemClick (PatientsRecyclerAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patients , parent , false);

        return new PatientsRecyclerAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.user.setText(list.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView user;


        public Holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.patient_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){

                        onItemClick.OnClick(list.get(getLayoutPosition()).getPhone() , list.get(getLayoutPosition()).getName() , list.get(getLayoutPosition()).getEmail()
                        , list.get(getLayoutPosition()).getDate() , list.get(getLayoutPosition()).getAge() , list.get(getLayoutPosition()).getDes());
                    }
                }
            });

        }
    }

    public interface OnItemClick{

        void OnClick(String phone , String name , String email  , String date , String age , String des);

    }
}
