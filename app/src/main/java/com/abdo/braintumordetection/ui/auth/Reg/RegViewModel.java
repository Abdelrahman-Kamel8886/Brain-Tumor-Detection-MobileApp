package com.abdo.braintumordetection.ui.auth.Reg;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdo.braintumordetection.models.RegModel;
import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegViewModel extends ViewModel {

    DatabaseReference drreg_ref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth drreg_auth = FirebaseAuth.getInstance();

    private StorageReference storage = FirebaseStorage.getInstance().getReference();
    private StorageReference sRef = FirebaseStorage.getInstance().getReference();

    MutableLiveData<Integer> loged = new MutableLiveData<>();


    public void Sign( Uri filePath  , String username , String email ,String phone, String password , String birth){
        drreg_auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                uploadFile(filePath  , username , email ,phone, password ,birth);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void uploadFile(Uri filePath  , String username , String email ,String phone, String password , String birth) {

        if (filePath != null) {
            sRef = storage.child("file/" + "profiles/"+ phone +"/" +System.currentTimeMillis());

            sRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    final StorageReference filePath = sRef;


                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String finalpath = uri.toString();


                            SendData(username,email,phone,password,birth,finalpath);
                        }
                    });
                }
            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


        }
    }

    private void SendData(String username , String email ,String phone, String password , String birth , String image){
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (id != null){
            SharedModel.setId(id);
            SharedModel.setUsername(username);
            drreg_ref.child(Consts.DR).child(id).setValue(new RegModel(username,email,phone , password , birth , image))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    login(email , password);
                }
            });
        }


    }
    private void login(String email ,String password ){
        drreg_auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                loged.setValue(1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


}
