package com.abdo.braintumordetection.models;

public class ModelPatient {

    String name;
    String phone;
    String email;
    String age;
    String des;
    String date;


    public ModelPatient() {
    }


    public ModelPatient(String name, String phone, String email, String age, String des , String date) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.des = des;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
