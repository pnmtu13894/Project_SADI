package com.minhtu.firebaseproject.Entities;

import java.util.HashMap;

/**
 * Created by minhtu on 4/30/17.
 */

public class Customer {

    private String mName;
    private String mEmail;
    private HashMap<String,Object> dateJoined;
    private boolean hasLoggedInWithPassword;

    public Customer() {
    }

    public Customer(String name, String email, HashMap<String, Object> dateJoined, boolean hasLoggedInWithPassword) {
        mName = name;
        mEmail = email;
        this.dateJoined = dateJoined;
        this.hasLoggedInWithPassword = hasLoggedInWithPassword;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public HashMap<String, Object> getDateJoined() {
        return dateJoined;
    }

    public boolean isHasLoggedInWithPassword() {
        return hasLoggedInWithPassword;
    }
}
