package com.abdo.braintumordetection.ui.dr.chat.endtoend;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.ModelChat;
import com.abdo.braintumordetection.models.ModelUser;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


    ArrayList<ModelChat> msgs = new ArrayList<>();
    MutableLiveData<ArrayList<ModelChat>> list = new MutableLiveData<>();




    public void getMassages (){

        String Chatid = SharedModel.getChat();

        ref.child(Consts.CHATS).child(Chatid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                msgs.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    msgs.add(snapshot1.getValue(ModelChat.class));
                }

                list.setValue(msgs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
