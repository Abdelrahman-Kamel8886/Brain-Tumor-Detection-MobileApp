package com.abdo.braintumordetection.ui.dr.result;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowResultViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    MutableLiveData<ArrayList<String>> list = new MutableLiveData<>();


    public void getData(){

        ref.child(Consts.PATIENTS_REF).child(SharedModel.getPhone()).child("r").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedModel.setResult_upload((String) snapshot.getValue());
                getData2();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getData2(){

        ref.child(Consts.PATIENTS_REF).child(SharedModel.getPhone()).child("l").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SharedModel.setLinks_upload((ArrayList<String>) snapshot.getValue());
                list.setValue(SharedModel.getLinks_upload());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
