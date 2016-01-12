package com.yifeishen.locationtrackersdk.user;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.yifeishen.locationtrackersdk.LT.OnTaskCompleteHandler;

import java.util.Map;

/**
 * Created by Flyaway on 1/11/16.
 */
public interface UserManager {
    public User getActiveUser();

    public boolean setActiveUser(User user);

    public boolean logoutUser();

    public void registerUser(String email, String pwd,Firebase.ValueResultHandler<Map<String, Object>> handler);

    public void loginUser(String email, String pwd, final OnTaskCompleteHandler<AuthData> handler);

}
