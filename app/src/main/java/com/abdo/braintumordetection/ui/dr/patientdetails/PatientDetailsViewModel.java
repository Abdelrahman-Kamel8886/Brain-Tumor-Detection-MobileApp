package com.abdo.braintumordetection.ui.dr.patientdetails;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.ModelPatient;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientDetailsViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> images = new ArrayList<>();
    MutableLiveData<ArrayList<String>> list = new MutableLiveData<>();


    public void getData(){
        images.clear();
        ref.child(Consts.PATIENTS_REF).child(SharedModel.getPhone()).child(Consts.PATIENTS_RES_REF).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    images.add(snapshot1.getValue(String.class));
                }
                list.setValue(images);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
