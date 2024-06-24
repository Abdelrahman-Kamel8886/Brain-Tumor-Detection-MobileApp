package com.abdo.braintumordetection.ui.dr.reports;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.ModelPatient;
import com.abdo.braintumordetection.utils.Consts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportsViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> phones = new ArrayList<>();
    ArrayList<String> phones2 = new ArrayList<>();

    ArrayList<ModelPatient> users = new ArrayList<>();
    MutableLiveData<ArrayList<ModelPatient>> list = new MutableLiveData<>();

    int i =0 ;


    public void getData(){
        phones.clear();
        ref.child(Consts.PATIENTS_REF).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 :snapshot.getChildren() ) {
                    phones.add(snapshot1.getKey());
                }
                getData2();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getData2(){

        for (String phone : phones) {

            ref.child(Consts.PATIENTS_REF).child(phone).child("Report").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    i+=1;
                    if (snapshot.getValue() != null) {

                        phones2.add(phone);

                    }

                    if (i==phones.size()){
                        getData3();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {



                }
            });
        }


    }

    private void getData3(){

        users.clear();
        for (String phone : phones2) {

            ref.child(Consts.PATIENTS_REF).child(phone).child(Consts.PATIENTS_INFO_REF).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.getValue(ModelPatient.class).getName();
                    String email = snapshot.getValue(ModelPatient.class).getEmail();
                    String age = snapshot.getValue(ModelPatient.class).getAge();
                    String des = snapshot.getValue(ModelPatient.class).getDes();
                    String date = snapshot.getValue(ModelPatient.class).getDate();
                    users.add(new ModelPatient(name , phone , email ,age , des , date));


                    if (users.size() == phones2.size()){
                        list.setValue(users);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }





    }

}
