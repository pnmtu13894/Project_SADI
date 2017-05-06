package com.minhtu.firebaseproject.Entities;

import java.util.HashMap;

/**
 * Created by minhtu on 4/30/17.
 */

public class User {

    private String userID;
    private String mName;
    private String mEmail;
    private int mRole;
    private HashMap<String,Object> dateJoined;
    private boolean hasLoggedInWithPassword;

    public User() {
    }

    public User(String name, String email, HashMap<String, Object> dateJoined, boolean hasLoggedInWithPassword, int role) {
        this.mName = name;
        this.mEmail = email;
        this.dateJoined = dateJoined;
        this.hasLoggedInWithPassword = hasLoggedInWithPassword;
        this.mRole = role;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getRole() {
        return mRole;
    }

    public void setRole(int role) {
        mRole = role;
    }
}
