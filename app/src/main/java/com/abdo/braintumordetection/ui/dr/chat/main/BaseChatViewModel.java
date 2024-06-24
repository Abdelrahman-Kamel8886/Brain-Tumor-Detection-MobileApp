package com.abdo.braintumordetection.ui.dr.chat.main;

import static com.abdo.braintumordetection.utils.SharedModel.getUsername;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.ModelUser;
import com.abdo.braintumordetection.models.RegModel;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BaseChatViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelUser> users = new ArrayList<>();
    MutableLiveData<ArrayList<ModelUser>> list = new MutableLiveData<>();


    public void getData(){
        users.clear();
        ref.child(Consts.DR).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    String name = snapshot1.getValue(RegModel.class).getUsername();
                    String phone = snapshot1.getValue(RegModel.class).getPhone();
                    String id = snapshot1.getKey();
                    String img = snapshot1.getValue(RegModel.class).getImage();


                    if(phone.equals(SharedModel.getDrphone())){

                    }
                    else{
                        users.add(new ModelUser(name,phone,id ,img));
                    }


                }
                list.setValue(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}


