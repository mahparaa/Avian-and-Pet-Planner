package com.example.mahpara.myapp;

/**
 * Created by Mahpara on 4/18/2018.
 */

public class RegisterModel {
    private String email;
    private String password;
    private String contact;

    public RegisterModel(String email, String password, String contact) {
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public RegisterModel() {
    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }
}
