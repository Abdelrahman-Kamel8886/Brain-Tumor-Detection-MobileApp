package com.abdo.braintumordetection.ui.auth.Login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.utils.SharedModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginViewModel extends ViewModel {

    DatabaseReference drreg_ref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth drreg_auth = FirebaseAuth.getInstance();
    MutableLiveData<Integer> loged = new MutableLiveData<>();
    MutableLiveData<Exception> notloged = new MutableLiveData<>();



    public void login(String email ,String password ){
        drreg_auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                SharedModel.setId(user_id);
                loged.setValue(1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                notloged.setValue(e);

            }
        });
    }


}
