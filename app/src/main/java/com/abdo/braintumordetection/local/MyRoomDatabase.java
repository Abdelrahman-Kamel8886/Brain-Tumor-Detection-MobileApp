package com.abdo.braintumordetection.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abdo.braintumordetection.models.ModelAuthCache;


@Database(entities = {ModelAuthCache.class},version = 2)
abstract public class MyRoomDatabase extends RoomDatabase {


    public static MyRoomDatabase myRoomDatabase;

    public abstract MyDao getDao();

    public static synchronized void initRoom(Context context){

        if (myRoomDatabase == null){
            myRoomDatabase = Room.databaseBuilder(context,MyRoomDatabase.class,"cache db").fallbackToDestructiveMigration().build();
        }
    }
    public static MyRoomDatabase getInstance(){
        return myRoomDatabase;
    }

}
