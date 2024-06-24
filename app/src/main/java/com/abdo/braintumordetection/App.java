package com.abdo.braintumordetection;

import android.app.Application;

import androidx.annotation.NonNull;

import com.abdo.braintumordetection.local.MyRoomDatabase;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        MyRoomDatabase.initRoom(this);
    }






}
