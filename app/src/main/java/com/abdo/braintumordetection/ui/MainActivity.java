package com.abdo.braintumordetection.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.abdo.braintumordetection.databinding.ActivityMainBinding;
import com.abdo.braintumordetection.local.MyRoomDatabase;
import com.abdo.braintumordetection.models.ModelAuthCache;
import com.abdo.braintumordetection.ui.auth.Login.LoginFragment;
import com.abdo.braintumordetection.ui.dr.home.DrHomeFragment;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getApiKey();
        getcache();
    }

    private void splash(){

        if(SharedModel.cache == false){
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(),new LoginFragment()).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(),new DrHomeFragment()).commit();
        }

    }

    private   void getcache(){
        MyRoomDatabase.getInstance().getDao().getall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ModelAuthCache>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ModelAuthCache> modelAuthCaches) {


                        if(modelAuthCaches.size()>0){
                            SharedModel.cache = true;
                            SharedModel.setId(modelAuthCaches.get(0).getUser_id());
                            SharedModel.setDrphone(modelAuthCaches.get(0).getUser_phone());
                            SharedModel.setUsername(modelAuthCaches.get(0).getUser_name());
                            SharedModel.setDrmail(modelAuthCaches.get(0).getEmail());
                            splash();
                        }
                        else{
                            splash();
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        splash();
                    }
                });
    }

    private void getApiKey(){

        ref.child("ApiKey").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SharedModel.setApi(snapshot.getValue(String.class)+"/");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}