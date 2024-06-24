package com.abdo.braintumordetection.ui.dr.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.RegModel;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DrHomeViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    MutableLiveData<RegModel> model = new MutableLiveData<>();



    public void getData(){
        ref.child(Consts.DR).child(SharedModel.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedModel.setId(snapshot.getKey());
                model.setValue(snapshot.getValue(RegModel.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}
