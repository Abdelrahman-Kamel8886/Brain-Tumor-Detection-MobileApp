package com.abdo.braintumordetection.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class ModelAuthCache {

    @PrimaryKey
    private  int  id = 0;

    private String user_name;
    private String user_id;
    private String user_phone;
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public ModelAuthCache() {
    }


    public ModelAuthCache(String user_name, String user_id, String user_phone, String email) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.user_phone = user_phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
