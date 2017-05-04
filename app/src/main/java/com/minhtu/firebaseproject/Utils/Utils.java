package com.minhtu.firebaseproject.Utils;

/**
 * Created by minhtu on 4/30/17.
 */

public class Utils {

    public static final String FIRE_BASE_URL = "https://fir-project-e7d74.firebaseio.com/";
    public static final String FIRE_BASE_USER_REFERENCE = FIRE_BASE_URL + "users/";
    public static final String FIRE_BASE_PRODUCT_REFERENCE = "https://fir-project-e7d74.firebaseio.com/shoes";

    public static String encodeEmail(String userEmail){
        return userEmail.replace(".", ",");
    }



}

