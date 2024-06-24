package com.abdo.braintumordetection.models;



public class RegModel {

    private String username;
    private String email;
    private String phone;
    private String password;
    private String birth;
    private String image;

    public RegModel() {
    }

    public RegModel(String username, String email, String phone, String password, String birth, String image) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.birth = birth;
        this.image = image;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
