package com.abdo.braintumordetection.ui.dr.patientdetails.WriteReport;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.TitleModel;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WriteReportViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public MutableLiveData<Integer> success = new MutableLiveData<>();


    public void SendData(){
        ref.child(Consts.PATIENTS_REF).child(SharedModel.getPhone()).child("Report").setValue("done").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                success.setValue(1);
            }
        });
    }

}
