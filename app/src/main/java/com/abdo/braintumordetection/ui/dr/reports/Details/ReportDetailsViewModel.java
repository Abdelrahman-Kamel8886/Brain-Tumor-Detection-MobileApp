package com.abdo.braintumordetection.ui.dr.reports.Details;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.TitleModel;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportDetailsViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ArrayList<TitleModel> reports = new ArrayList<>();
    MutableLiveData<ArrayList<TitleModel>> list = new MutableLiveData<>();


    public void getData(){
        reports.clear();
        ref.child(Consts.PATIENTS_REF).child(SharedModel.getPhone()).child("Report").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    reports.add(snapshot1.getValue(TitleModel.class));
                }
                list.setValue(reports);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
