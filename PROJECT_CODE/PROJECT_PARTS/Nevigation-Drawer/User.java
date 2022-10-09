package com.mahpara.pet_lover_nevigation_fragments;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by cell  spot on 8/30/2017.
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}