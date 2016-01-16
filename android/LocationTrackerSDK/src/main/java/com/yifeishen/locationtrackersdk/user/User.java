package com.yifeishen.locationtrackersdk.user;

/**
 * Created by Flyaway on 1/11/16.
 */
public class User {
    private String email;
    private String uid;

    User(String email, String uid){
        this.email = email;
        this.uid = uid;
    }

    public String getEmail(){
        return email;
    }

    public String getUid(){
        return uid;
    }

    public void setEmail(String s){
        email = s;
    }

    public void setUid(String s){
        uid = s;
    }
}
